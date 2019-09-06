package com.hongyuan.fitness.ui.person.setting.agreement;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAgreementBinding;

public class AgreementActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected void initView() {
        setTitle("服务协议");
        setsetImmersive(0x55000000);
        ActivityAgreementBinding binding = ActivityAgreementBinding.bind(mView);
        AgreementViewModel viewModel = new AgreementViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
