package com.hongyuan.fitness.ui.only_equipment.indicator_details;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.service.BluetoothStateBroadcastReceive;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.device_list.WristbandDeviceListFragment;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.prompt.WristbandPromptFragment;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.wristband_data_detail.WristbandDeviceDataFragment;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.wristband_inface.WristbandStatusListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.yolanda.health.qnblesdk.out.QNBleDevice;

import org.greenrobot.eventbus.EventBus;

public class WristbandFragment extends CustomFragment implements WristbandStatusListener, BluetoothStateBroadcastReceive.BluetoothStateListener {


    //声明本次使用到的java类
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    //提示页面的Fragment
    private WristbandPromptFragment promptFragment;
    //设备集合页面
    private WristbandDeviceListFragment deviceListFragment;
    //手环数据详情页面
    private WristbandDeviceDataFragment deviceDataFragment;

    //手环连接状态
    private WristbandStautsUtils stautsUtils;

    //蓝牙设备
    private BluetoothAdapter mBluetoothAdapter;
    //蓝牙监听器
    private BluetoothStateBroadcastReceive mReceive;

    //存储的上一次连接的设备mac地址
    private String deviceMac;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wristband;
    }

    @Override
    public void initView(View mView) {
        //获取存储的deviceMac
        deviceMac = (String) SharedPreferencesUtil.getBean(mActivity,"deviceMac");

        //手环连接状态的监听
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);
        //设置页面回调监听
        stautsUtils.setStatusListener(this);
        //获取蓝牙对象
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        fragmentManager=getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        promptFragment = new WristbandPromptFragment();
        deviceListFragment = new WristbandDeviceListFragment();
        deviceDataFragment = new WristbandDeviceDataFragment();

        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.contentBox,deviceListFragment)
                .add(R.id.contentBox,deviceDataFragment)
                .add(R.id.contentBox,promptFragment);

        //判断蓝牙是否打开
        if(!mBluetoothAdapter.isEnabled()){
            //动态注册蓝牙监听
            registerBluetoothReceiver();
            //打开蓝牙
            mBluetoothAdapter.enable();
        }else{
            //控制显示情况
            showPage();
        }

    }

    /*
    * 页面显示控制操作
    * */
    private void showPage(){
        if(!BaseUtil.isValue(deviceMac)){
            fragmentTransaction.hide(deviceListFragment).hide(deviceDataFragment).show(promptFragment).commit();
        }else{
            //启动自动连接功能
            stautsUtils.setDeviceMac(deviceMac);
            //查看是否已连接并绑定
            if(stautsUtils.isBand()){
                //已连接直接显示数据页面
                fragmentTransaction.hide(deviceListFragment).hide(promptFragment).show(deviceDataFragment).commit();
                //通知可以显示手环设置图标了
                EventBus.getDefault().post(ConstantsCode.EB_WRISTBAND_SHOW_CONTENT,"0");
            }else{
                //先要执行这句，不然会报null指针异常
                fragmentTransaction.hide(deviceListFragment).hide(promptFragment).hide(deviceDataFragment).commit();
                //未连接，执行自动搜索并连接绑定操作
                stautsUtils.goSearch();
            }
        }


    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
    * 动态注册蓝牙监听器
    * */
    private void registerBluetoothReceiver(){
        if(mReceive == null){
            mReceive = new BluetoothStateBroadcastReceive();
            mReceive.setStateListener(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_OFF");
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_ON");
        mActivity.registerReceiver(mReceive, intentFilter);
    }

    /*
    * 注销蓝牙监听
    * */
    private void unregisterBluetoothReceiver(){
        if(mReceive != null){
            mActivity.unregisterReceiver(mReceive);
            mReceive = null;
        }
    }

    @Override
    public void statusListener(int code, QNBleDevice qnBleDevice) {
        if(code == WristbandStatusListener.PROMPT){
            fragmentManager=getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(deviceListFragment).hide(deviceDataFragment)
                    .show(promptFragment).commit();
        }

        if(code == WristbandStatusListener.SEREACH_LIST){
            fragmentManager=getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(promptFragment).hide(deviceDataFragment)
                    .show(deviceListFragment).commit();
            deviceListFragment.changeData(qnBleDevice);
        }

        if(code == WristbandStatusListener.WRISTBAND_DATA){
            fragmentManager=getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.hide(promptFragment).hide(deviceListFragment)
                    .show(deviceDataFragment).commit();
            //通知可以显示设置图标了
            EventBus.getDefault().post(ConstantsCode.EB_WRISTBAND_SHOW_CONTENT,"0");
            //关闭继续搜索
            //promptFragment.setConnect(true);
            //获取所有信息
            deviceDataFragment.getAllData();

            isBand();
        }
    }

    /*
    * 通知设备已绑定成功
    * */
    private void isBand(){
        clearParams();
        Controller.myRequest(Constants.DEVICE,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBluetoothReceiver();
    }

    /*
    * 蓝牙状态监听回调
    * */
    @Override
    public void bluetoothState(int code) {
        if(code == BluetoothAdapter.STATE_ON){
            showPage();
        }else{
            mActivity.showErr("蓝牙已被关闭！");
        }
    }
}
