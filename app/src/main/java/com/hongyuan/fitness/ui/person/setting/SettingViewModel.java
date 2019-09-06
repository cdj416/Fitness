package com.hongyuan.fitness.ui.person.setting;

import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivitySettingBinding;
import com.hongyuan.fitness.ui.login.LoginBean;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.util.CacheUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

public class SettingViewModel extends CustomViewModel {

    private ActivitySettingBinding binding;

    public SettingViewModel(CustomActivity mActivity, ActivitySettingBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
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
    }

    @Override
    public void onSuccess(Object data) {

    }
}
