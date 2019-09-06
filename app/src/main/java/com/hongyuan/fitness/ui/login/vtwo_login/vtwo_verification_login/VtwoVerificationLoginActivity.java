package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityVtwoVerificationCodeLoginBinding;

public class VtwoVerificationLoginActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_verification_code_login;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoVerificationCodeLoginBinding binding = ActivityVtwoVerificationCodeLoginBinding.bind(mView);
        VtwoVerificationLoginViewModel viewModel = new VtwoVerificationLoginViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
