package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_modify;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityVtwoModifyPasswordBinding;

public class VtwoModifyPasswordActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_modify_password;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoModifyPasswordBinding binding = ActivityVtwoModifyPasswordBinding.bind(mView);
        VtwoModifyPasswordViewModel viewModel = new VtwoModifyPasswordViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
