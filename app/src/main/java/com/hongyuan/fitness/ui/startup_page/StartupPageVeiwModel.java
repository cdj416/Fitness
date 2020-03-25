package com.hongyuan.fitness.ui.startup_page;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityStartupPageBinding;
import com.hongyuan.fitness.ui.advertising.AdvertisingPageActivity;
import com.hongyuan.fitness.ui.carousel.CarouselActivity;
import com.hongyuan.fitness.ui.login.LoginBean;
import com.hongyuan.fitness.ui.login.TokenBean;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.out_door.wallk.TodayStepUtils;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.EncryptionUtil;
import com.hongyuan.fitness.util.HourMeterUtil;
import com.hongyuan.fitness.util.MyLocationUtil;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.hongyuan.fitness.util.SystemUtil;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.today.step.lib.TodayStepManager;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StartupPageVeiwModel extends CustomViewModel implements CustomDialog.DialogClick, HourMeterUtil.TimeCallBack {

    //登录所存储的数据
    private LoginBean.DataBean loginBean;
    //是否第一次使用所存储的数据
    private FistUseBean fistUseBean;

    //需要申请的权限，必须先在AndroidManifest.xml有声明，才可以动态获取权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.VIBRATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION};
    private RxPermissions rxPermissions = null;
    private String promptMessage = "请授权，否则无法使用该应用,请谅解！";

    //计时3秒进入主界面
    private HourMeterUtil hourMeterUtil;

    //读取广告
    private HomeBannerBean.DataBean advertImgBean;

    public StartupPageVeiwModel(CustomActivity mActivity, ActivityStartupPageBinding binding) {
        super(mActivity);
        initView();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);

        //权限申请
        rxPermissions = new RxPermissions(mActivity);
        rxPermissions.request(PERMISSIONS_STORAGE).subscribe(aBoolean -> {
            if (!aBoolean) {// 用户拒绝了权限
                CustomDialog.promptDialog(mActivity,promptMessage,"开启授权","取消",false,this);
            }else{
                if(!SystemUtil.checkState_23orNew(mActivity)){
                    CustomDialog.showMessage(mActivity,"网络无法连接，请重启！");
                }else{
                    goGoGo();
                }
            }
        });

    }

    /*
    * 当权限通过时，需要做的操作
    * */
    private void goGoGo(){
        //开启定位
        MyLocationUtil.getInstance().startLocation();
        //检测是否第一次使用
        fistUseBean = (FistUseBean) SharedPreferencesUtil.getBean(mActivity,"firstUse");
        //获取登录信息
        loginBean = (LoginBean.DataBean) SharedPreferencesUtil.getBean(mActivity,LOGIN_SESSION);

        //token信息
        TokenBean.DataBean tokenBean = (TokenBean.DataBean) SharedPreferencesUtil.getBean(mActivity,TOKEN_SESSION);
        setTokens(tokenBean);

        //初始化计步模块
        TodayStepManager.startTodayStepService(mActivity.getApplication());
        //启动获取几步数据
        TodayStepUtils.getInstance();
        //请求最新token
        lazyLoad();

        //启动计时
        hourMeterUtil.startCount();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("client","Android").setParams("at_name","fit").setParams("at_pwd", EncryptionUtil.md5DecodeTwo("123456"));
        Controller.myRequest(Constants.GET_TOKEN,Controller.TYPE_POST,getParams(), TokenBean.class,this);
    }

    /*
    * 读取广告数据
    * */
    private void getLogData(){
        //读取广告
        clearParams().setParams("img_code","app_ad ").setParams("img_num","1");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        //存储token信息
        if((data instanceof TokenBean) && isSuccess(data)){
            TokenBean.DataBean reqToken = ((TokenBean)data).getData();
            //存储用户Token信息
            SharedPreferencesUtil.putBean(mActivity,TOKEN_SESSION,reqToken);
            setTokens(reqToken);
        }

        if(data instanceof HomeBannerBean){
            advertImgBean = ((HomeBannerBean)data).getData();
        }
    }

    /*
    * 写入token信息
    * */
    private void setTokens(TokenBean.DataBean tokenBean){
        if(BaseUtil.isValue(tokenBean)){
            userToken.setAt_id(tokenBean.getAt_id());
            userToken.setAt_name(tokenBean.getAt_name());
            userToken.setAt_pwd(tokenBean.getAt_pwd());
            userToken.setToken(tokenBean.getToken());

            //如果之前有登录过，就设置登录信息
            if(loginBean != null){
                userToken.setM_id(loginBean.getM_id());
                userToken.setM_mobile(loginBean.getM_mobile());

                //去注册登录环信账号。
                if(!HuanXinUtils.getInstance().isConnect()){
                    HuanXinUtils.getInstance().registerdHuanXin(userToken.getM_mobile());
                }
            }

            //去获取广告数据
            getLogData();
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void dialogClick(View v) {
        if(v.getId() == R.id.isCannel){
            System.exit(0);
        }
        if(v.getId() == R.id.isOk){
            rxPermissions.request(PERMISSIONS_STORAGE).subscribe(aBoolean -> {
                if (!aBoolean) {// 用户拒绝了权限
                    CustomDialog.promptDialog(mActivity,promptMessage,"开启授权","取消",false,this);
                }else{
                    goGoGo();
                }
            });
        }
    }


    /*
    * 计时完成操作
    * */
    @Override
    public void onTime(int passedTime) {
        if(passedTime == 3){
            //停止计时
            hourMeterUtil.stopCount();
            //跳转到主界面
            if(fistUseBean == null){
                Bundle bundle = new Bundle();
                if(advertImgBean != null && advertImgBean.getList() != null && advertImgBean.getList().size() > 0){
                    bundle.putSerializable("advertImgBean",advertImgBean.getList().get(0));
                }else{
                    bundle.putSerializable("advertImgBean",null);
                }
                startActivity(CarouselActivity.class,bundle);
            }else{
                if(advertImgBean != null && advertImgBean.getList() != null && advertImgBean.getList().size() > 0){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("advertImgBean",advertImgBean.getList().get(0));
                    startActivity(AdvertisingPageActivity.class,bundle);
                }else{
                    startActivity(MainActivity.class,null);
                }
                mActivity.finish();
            }
        }
    }
}
