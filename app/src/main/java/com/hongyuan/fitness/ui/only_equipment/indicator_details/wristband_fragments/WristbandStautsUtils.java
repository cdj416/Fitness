package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments;
import android.util.Log;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.wristband_inface.WristbandStatusListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.HourMeterUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.qingniu.qnble.utils.QNLogUtils;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.constant.QNDeviceStatus;
import com.yolanda.health.qnblesdk.listener.QNBindResultCallback;
import com.yolanda.health.qnblesdk.listener.QNBleConnectionChangeListener;
import com.yolanda.health.qnblesdk.listener.QNBleDeviceDiscoveryListener;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;
import com.yolanda.health.qnblesdk.out.QNBleBroadcastDevice;
import com.yolanda.health.qnblesdk.out.QNBleDevice;
import com.yolanda.health.qnblesdk.out.QNBleKitchenDevice;
import com.yolanda.health.qnblesdk.out.QNUser;

import java.util.Date;

public class WristbandStautsUtils implements HourMeterUtil.TimeMoreCallBack{

    private static WristbandStautsUtils stautsUtils = null;

    private int ONE_CODE = 0X01;
    private int TWO_CODE = 0X02;
    private static CustomActivity mContext;
    //蓝牙工具类
    private QNBleApi mQNBleApi;
    //手环命令管理器
    private QNBandManager bandManager;
    //手环是否处于连接状态
    private boolean isConnect;
    //手环是否处于连接绑定状态中
    private boolean isBand;
    //链接的设备对象
    private QNBleDevice device;
    //计时器
    private HourMeterUtil hourUtil;
    //页面控制监听回调
    private WristbandStatusListener statusListener;
    //用户信息
    private QNUser qnUser;
    //是否开启自动扫描，自动连接，自动绑定操作
    private boolean auto;
    //已连过的设备mac地址
    private String deviceMac;

    private WristbandStautsUtils(){
        mQNBleApi = QNBleApi.getInstance(mContext);
        bandManager = mQNBleApi.getBandManager();
        //计时回调
        hourUtil = new HourMeterUtil();
        hourUtil.setTimeMoreCallBack(this);
        //创建用户信息
        buildQNUser();
        //设备蓝牙连接监听
        initBleState();
        //设备扫描监听
        setSearchLinstener();
    }

    public static WristbandStautsUtils getInstance(CustomActivity context){
        //这个属性需要从新初始化，不然会报错
        mContext = context;
        if(stautsUtils == null){
            stautsUtils = new WristbandStautsUtils();
        }


        return stautsUtils;
    }

    /*
    * 页面控制监听
    * */
    public void setStatusListener(WristbandStatusListener statusListener) {
        this.statusListener = statusListener;
    }

    /*
    * 开启心率监听模式
    * */
    private void setHeartRate(){
        bandManager.syncHeartRateObserverMode(true, 6, (code, msg) -> Log.e("cdj","=======心率监听模式======"+code+"======="+msg));
    }

    /**
     * 监听蓝牙状态变化
     */
    private void initBleState() {
        mQNBleApi.setBleConnectionChangeListener(new QNBleConnectionChangeListener() {
            @Override
            public void onConnecting(QNBleDevice device) {
                isConnect = false;
                isBand = false;
            }

            @Override
            public void onConnected(QNBleDevice device) {
                isConnect = true;
                isBand = false;
            }

            @Override
            public void onServiceSearchComplete(QNBleDevice device) {
                isConnect = false;
            }

            @Override
            public void onDisconnecting(QNBleDevice device) {
                isConnect = false;
                isBand = false;
            }

            @Override
            public void onDisconnected(QNBleDevice device) {
                isConnect = false;
                isBand = false;
            }

            @Override
            public void onConnectError(QNBleDevice device, int errorCode) {
                Log.e("cdj","=========断开原因===="+errorCode);
                isConnect = false;
                isBand = false;
            }

            @Override
            public void onDeviceStateChange(QNBleDevice device, int status) {
                Log.e("cdj","============连接状态改变======="+status);
                if(status == QNDeviceStatus.STATE_READY){//已经准备好，并可以开始绑定设备了
                    isConnect = true;
                    //绑定手环
                    bandDevice();
                }else{
                    isBand = false;
                    if(status == QNDeviceStatus.STATE_CONNECTED){
                        isConnect = true;
                    }else{
                        isConnect = false;
                    }
                }
            }

        });
    }

    public boolean isBand() {
        return isBand;
    }

    public QNBleDevice getDevice() {
        return device;
    }

    /*
    * 设置存储到手机的设备mac地址(并启动自动扫描连接功能)
    * */
    public void setDeviceMac(String deviceMac){
        this.deviceMac = deviceMac;
        auto = true;
    }

    /*
    * 清除所有数据，清楚mac地址信息和连接的设备信息
    * */
    public void clearMac(){
        SharedPreferencesUtil.putBean(mContext,"deviceMac","");
        this.deviceMac = null;
        this.device = null;
        auto = false;
        this.mContext = null;
        this.stautsUtils = null;
    }


    /**
     * 构建手环用户
     */
    private void buildQNUser() {
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        qnUser = mQNBleApi.buildUser(mContext.userToken.getM_mobile(), 170, "male", date, 65.5, (code, msg) -> Log.e("cdj","======="+code+"========"+msg));
    }

    /*
    * 设备扫描监听
    * */
    public void setSearchLinstener(){
        mQNBleApi.setBleDeviceDiscoveryListener(new QNBleDeviceDiscoveryListener() {

            @Override
            public void onDeviceDiscover(QNBleDevice qnBleDevice) {
                if(BaseUtil.isValue(deviceMac) && auto){
                    if(qnBleDevice.getMac().equals(deviceMac)){
                        //启动连接手环
                        goConnect(qnBleDevice);
                    }
                }else{
                    hourUtil.stopCount();
                    mContext.showLemonSuccess("搜索到设备！");
                    //第一次使用设备，需要用户主动扫码，并选择设备连接
                    if(statusListener != null && !isConnect){
                        statusListener.statusListener(WristbandStatusListener.SEREACH_LIST,qnBleDevice);
                    }
                }

            }

            @Override
            public void onStartScan() {

            }

            @Override
            public void onStopScan() {

            }

            @Override
            public void onScanFail(int i) {

            }

            @Override
            public void onBroadcastDeviceDiscover(QNBleBroadcastDevice qnBleBroadcastDevice) {
                Log.e("cdj","=============扫描成功1======="+qnBleBroadcastDevice.getBluetoothName());
            }

            @Override
            public void onKitchenDeviceDiscover(QNBleKitchenDevice qnBleKitchenDevice) {
                Log.e("cdj","=============扫描成功2======="+qnBleKitchenDevice.getName());
            }
        });
    }

    /*
    * 开始扫描手环设备
    * */
    public void goSearch(){
        mContext.showLemonLoading("扫描中...");
        hourUtil.startCount(ONE_CODE);
        mQNBleApi.startBleDeviceDiscovery((code, msg) -> Log.e("cdj", "code:" + code + ";msg:" + msg));
    }

    /*
    * 停止扫描设备
    * */
    public void stopSearch(){
        //停止设备的扫描
        mQNBleApi.stopBleDeviceDiscovery(new QNBindResultCallback() {
            @Override
            public void onStatusResult(int i) {

            }

            @Override
            public void onConfirmBind() {

            }

            @Override
            public void onResult(int i, String s) {

            }
        });
    }

    /*
    * 连接手环
    * */
    public void goConnect(QNBleDevice qnBleDevice){
        //停止搜索设备
        stopSearch();
        mContext.showLemonLoading("连接中...");
        mQNBleApi.connectDevice(qnBleDevice, qnUser, (code, msg) -> {
            //连接状态
            if (code == CheckStatus.OK.getCode()) {
                //停止计时
                hourUtil.stopCount();

                if(statusListener != null){
                    //设置链接的设备对象
                    device = qnBleDevice;

                    //绑定手环
                    //bandDevice();
                }
            }else {
                mContext.showLemonErr("连接失败，请从新尝试！");
            }

        });
    }

    /*
     * 绑定手环
     * */
    private void bandDevice(){
        bandManager.bindBand(qnUser.getUserId(), new QNBindResultCallback() {
            @Override
            public void onStatusResult(int bindStatus) {
                if(bindStatus == 101){
                    if(auto){
                        mContext.showErr("该设备已被绑定，请搜索新的设备！");
                        clearMac();
                    }else{
                        mContext.showLemonErr("该设备已被绑定，请重新选择设备！");
                    }
                }

                if (bindStatus == 100 || bindStatus == 102) {
                    //存储连接的设备的设备mac
                    SharedPreferencesUtil.putBean(mContext,"deviceMac",device.getMac());
                    //连接并绑定成功
                    isBand = true;
                    //关闭页面加载框
                    mContext.hidLEmon();
                    //开启心率监听模式
                    setHeartRate();
                    //页面变化操作
                    statusListener.statusListener(WristbandStatusListener.WRISTBAND_DATA,null);
                }
            }

            @Override
            public void onConfirmBind() {
                QNLogUtils.error("请确认弹窗");
            }

            @Override
            public void onResult(int code, String msg) {
                if(code == CheckStatus.OK.getCode()){
                    Log.e("cdj","========绑定成功！=========");

                }else{
                    //清楚上一次的计时
                    //hourUtil.stopCount();
                    //启动循环绑定计时操作（因为不知道什么原因，第一次绑定总是失败）
                    //hourUtil.startCount(TWO_CODE);
                    Log.e("cdj","========绑定失败=========");
                }
            }
        });
    }

    @Override
    public void onTime(int code, int passedTime) {
        if(code == ONE_CODE){
            if(passedTime%30 == 0 && passedTime != 0){
                hourUtil.stopCount();
                mContext.showLemonErr("自动连接失败，请检查设备。");
                if(statusListener != null && !isConnect){
                    statusListener.statusListener(WristbandStatusListener.PROMPT,null);
                }
            }
        }

        if(code == TWO_CODE){
            //五秒去执行一次绑定操作
            if(passedTime%5 == 0 && !isBand){
                bandDevice();
            }
        }
    }
}
