package com.hongyuan.fitness.ui.person.about_us;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityAboutUsBinding;
import com.hongyuan.fitness.ui.person.setting.agreement.AgreementActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class AboutUsViewModel extends CustomViewModel {

    private ActivityAboutUsBinding binding;

    public AboutUsViewModel(CustomActivity mActivity, ActivityAboutUsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        //获取版本信息
        PackageManager manager = mActivity.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            name = info.versionName;
            binding.banBen.setText(name+"版本");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        binding.callTel.setOnClickListener(new View.OnClickListener() {
            @SingleClick(2000)
            @Override
            public void onClick(View v) {
                CustomDialog.callTel(mActivity, "(0572)2075532", v1 -> {
                    callTel("05722075532");
                });
            }
        });

    }

    //查看用户协议
    public BindingCommand goAgreement = new BindingCommand(() -> startActivity(AgreementActivity.class,null));
    public BindingCommand goYinSi = new BindingCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("url","http://www.hongyuangood.com/xy/xy.html");
        startActivity(WebViewActivity.class,bundle);});

    @Override
    public void onSuccess(Object data) {

    }
}
