package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.person_cloum.PersonItemCloumView;
import com.hongyuan.fitness.databinding.ActivityWristbandSettingBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.AlarmClockBeans;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.WristbandAlarmClockActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_msg.WristbandMsgActivity;
import com.hongyuan.fitness.ui.show_big_img.ShowBigImgActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.yolanda.health.qnblesdk.bean.QNSitRemind;
import com.yolanda.health.qnblesdk.bean.QNWeek;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.listener.QNObjCallback;
import com.yolanda.health.qnblesdk.listener.QNResultCallback;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;

public class WristbandSettingViwModel extends CustomViewModel {

    private ActivityWristbandSettingBinding binding;

    //蓝牙扫描类
    private QNBleApi mQNBleApi;
    //发送命令
    private QNBandManager bandManager;

    //配置数据
    private WristbandSettingBeans.DataBean setttingBeans;

    public WristbandSettingViwModel(CustomActivity mActivity, ActivityWristbandSettingBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();

    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        mQNBleApi = QNBleApi.getInstance(mActivity);
        bandManager = mQNBleApi.getBandManager();


        //闹钟设置
        binding.goAlarmClock.setGoClick(() -> {
            startActivity(WristbandAlarmClockActivity.class,null);
        });

        //消息提醒
        binding.msgRemind.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("msg_notice",setttingBeans.getInfo().getMsg_notice());
            bundle.putInt("wx_notice",setttingBeans.getInfo().getWx_notice());
            bundle.putInt("qq_notice",setttingBeans.getInfo().getQq_notice());
            startActivity(WristbandMsgActivity.class,bundle);
        });

        //久坐提醒
        binding.sitSwitch.setOnClickListener(v -> {
            setSedentary(binding.sitSwitch.isChecked());
        });

        //来电提醒
        binding.telSwitch.setOnClickListener(v -> {
            if(binding.telSwitch.isChecked()){
                setCallTel("陌生来电","17173175177");
            }
        });

        //抬手亮屏
        binding.twSwitch.setOnClickListener(v -> {
            tsSet(binding.twSwitch.isChecked());
        });

        //设置拍照模式
        binding.takePhotoSwitch.setOnClickListener(v -> {
            setTakePhoto(binding.takePhotoSwitch.isChecked());
        });

        //心率检测
        binding.xlSwitch.setOnClickListener(v -> {
            if(!binding.xlSwitch.isChecked()){
                binding.xlSwitch.setChecked(true);
                CustomDialog.promptDialog(mActivity, "关闭之后将无法查看心率信息，确定要关闭心率监测？", "确定", "取消", false, v1 -> {
                    if(v1.getId() == R.id.isOk){
                        binding.xlSwitch.setChecked(false);
                        setHeartRate(false);
                    }
                });
            }else{
                setHeartRate(true);
            }
        });

        //重启设备
        binding.fromDevice.setGoClick(() -> {
            CustomDialog.promptDialog(mActivity, "确定要重启手环？重启所有数据都将会清除！", "确定", "取消", false, v -> {
                if(v.getId() == R.id.isOk){
                    setReboot();
                }
            });

        });

        //产品说明
        binding.goProductManual.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("img",R.mipmap.product_manual_img);
            bundle.putString("title","产品说明");
            startActivity(ShowBigImgActivity.class,bundle);
        });
        //常见问题
        binding.goCommonProblem.setGoClick(() -> {
            Bundle bundle = new Bundle();
            bundle.putInt("img",R.mipmap.common_problem_img);
            bundle.putString("title","常见问题");
            startActivity(ShowBigImgActivity.class,bundle);
        });

        //获取手环基本信息
        setBattery();
    }

    /*
    * 状态刷新
    * */

    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
    * 久坐提醒
    * */
    private void setSedentary(boolean isOpen){
        int interval = 59;

        QNSitRemind qnSitRemind = new QNSitRemind();
        qnSitRemind.setOpenFlag(isOpen);
        qnSitRemind.setStartHour(0);
        qnSitRemind.setStartMinute(0);
        qnSitRemind.setEndHour(23);
        qnSitRemind.setEndMinute(0);

        QNWeek qnWeek1 = new QNWeek();
        qnWeek1.setMon(true);
        qnWeek1.setTues(true);
        qnWeek1.setWed(true);
        qnWeek1.setThur(true);
        qnWeek1.setFri(true);
        qnWeek1.setSat(true);
        qnWeek1.setSun(true);

        qnSitRemind.setWeek(qnWeek1);
        qnSitRemind.setMinuteInterval(interval);

        bandManager.syncSitRemind(qnSitRemind, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                //上传服务器配置
                updataSetting("site_long_notice",isOpen ? "1" : "0");
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }

        });
    }

    /*
    * 开启来电提醒
    * */
    private void setCallTel(String userName,String userPhone){
        bandManager.callRemind(userName, userPhone, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                //上传服务器配置
                updataSetting("call_notice","1");
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }
        });
    }

    /*
    * 抬手亮屏
    * */
    private void tsSet(boolean isOpen){
        bandManager.syncHandRecognizeMode(isOpen, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                //上传服务器配置
                updataSetting("bright_screen",isOpen ? "1" : "0");
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }
        });
    }

    /*
    * 心率检测
    * */
    private void setHeartRate(boolean isOpen){
        bandManager.syncHeartRateObserverMode(isOpen, 100, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                //上传服务器配置
                updataSetting("heart_rate",isOpen ? "1" : "0");
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }
        });
    }
    /*
    * 设置拍照模式
    * */
    private void setTakePhoto(boolean isOpen){
        bandManager.syncCameraMode(isOpen, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                Log.e("cdj","=====设置成功======");
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }
        });
    }

    /*
    * 重启手环
    * */
    private void setReboot(){
        bandManager.reboot((code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                mActivity.showSuccess("重启成功!", MainActivity.class,null);
            }else{
                onError(-1,"连接已断开，请退出并从新连接！");
            }
        });
    }

    /*
     * 获取手环信息
     * */
    private void setBattery(){
        bandManager.fetchBandInfo((data, code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                binding.wPower.setText("已连接 "+data.getElectric()+"%");
                binding.firmwareVersion.setRightText(String.valueOf(data.getFirmwareVer()));
            }
        });

        //设置mac
        binding.deviceMac.setRightText(WristbandStautsUtils.getInstance(mActivity).getDevice().getMac());
    }

    /*
     * 解除绑定
     * */
    public void unBind(){
        bandManager.cancelBind(mActivity.userToken.getM_mobile(), (data, code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                //接触绑定，清空手机存储的设备信息
                SharedPreferencesUtil.putBean(mActivity,"deviceMac","");
                //清楚app应用缓存的设备信息
                WristbandStautsUtils.getInstance(mActivity).clearMac();
                mActivity.showSuccess("绑定解除成功!", MainActivity.class,null);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //获取手环基本配置情况
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_SH_CONFIG_INFO,Controller.TYPE_POST,getParams(), WristbandSettingBeans.class,this);
    }

    /*
    * 添加手环配置到服务器
    * */
    private void updataSetting(String key,String value){
        //mActivity.showLoading();
        clearParams().setParams(key,value);
        Controller.myRequest(Constants.ADD_SH_CONFIG,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof WristbandSettingBeans){
            setttingBeans = ((WristbandSettingBeans)data).getData();
            if(setttingBeans != null && setttingBeans.getInfo() != null){
                WristbandSettingBeans.DataBean.InfoBean infoBean = setttingBeans.getInfo();

                if(1 == infoBean.getAlarm_notice()){
                   binding.goAlarmClock.setRightText("已添加");
                }else{
                    binding.goAlarmClock.setRightText("未添加");
                }

                if(1 == infoBean.getWx_notice() || 1 == infoBean.getQq_notice() || 1 == infoBean.getMsg_notice()){
                    binding.msgRemind.setRightText("已开启");
                }else{
                    binding.msgRemind.setRightText("未开启");
                }

                if(1 == infoBean.getSite_long_notice()){
                    binding.sitSwitch.setChecked(true);
                }else{
                    binding.sitSwitch.setChecked(false);
                }

                if(1 == infoBean.getCall_notice()){
                    binding.telSwitch.setChecked(true);
                }else{
                    binding.telSwitch.setChecked(false);
                }

                if(1 == infoBean.getBright_screen()){
                    binding.twSwitch.setChecked(true);
                }else{
                    binding.twSwitch.setChecked(false);
                }

                if(1 == infoBean.getHeart_rate()){
                    binding.xlSwitch.setChecked(true);
                }else{
                    binding.xlSwitch.setChecked(false);
                }
            }
        }
    }
}
