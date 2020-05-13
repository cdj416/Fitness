package com.hongyuan.fitness.ui.person.setting;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivitySettingBinding;
import com.hongyuan.fitness.ui.login.LoginBean;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_person.PersonBean;
import com.hongyuan.fitness.ui.person.edit_information.take_photo.TakePhotoActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CacheUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

public class SettingViewModel extends CustomViewModel {

    private ActivitySettingBinding binding;

    private PersonBean.DataBean.InfoBean personMessageBeans;

    public SettingViewModel(CustomActivity mActivity, ActivitySettingBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        try {
            binding.cacheNum.setText(CacheUtil.getTotalCacheSize(mActivity));
        }catch (Exception e){
            e.printStackTrace();
        }

        binding.cacheBox.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CacheUtil.clearAllCache(mActivity);
                try {
                    binding.cacheNum.setText(CacheUtil.getTotalCacheSize(mActivity));
                }catch (Exception e){
                    e.printStackTrace();
                }
                showSuccess("缓存清除成功！");
            }
        });

        binding.getOut.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CustomDialog.promptDialog(mActivity, "确定要退出登录吗？", "确定", "取消", false, v1 -> {
                    if(v1.getId() == R.id.isOk){
                        //置空登录信息
                        LoginBean.DataBean login = new LoginBean.DataBean();
                        //情况页面数据
                        SharedPreferencesUtil.putBean(mActivity,LOGIN_SESSION,login);
                        //通知首页变换数据及显示页面
                        EventBus.getDefault().post(ConstantsCode.EB_START_MAIN,"");

                        startActivity(MainActivity.class,null);
                    }
                });
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void setData() {
        if(BaseUtil.isValue(personMessageBeans.getMi_face())){
            binding.faceText.setText("已录入");
        }else{
            binding.faceText.setText("未录入");
            //弹出拍照提示框
            binding.takePhoto.setOnClickListener(v -> CustomDialog.showTakePhoto(mActivity, v13 -> {
                startActivityForResult(TakePhotoActivity.class,null);
            }));
        }
    }

    @Override
    public void forResult(Bundle bundle) {
        boolean isSuccess = bundle.getBoolean("isSuccess");
        if(isSuccess){
            binding.takePhoto.setClickable(false);
            binding.faceText.setText("已录入");
        }

    }

    @Override
    protected void lazyLoad() {
        clearParams();
        Controller.myRequest(Constants.GET_MEMBER_INDEX_INFO,Controller.TYPE_POST,getParams(), PersonBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PersonBean){
            personMessageBeans = ((PersonBean)data).getData().getInfo();
            setData();
        }
    }
}
