package com.hongyuan.fitness.ui.promt_success;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SuccessBinding;

public class V3SuccessActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_success;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,0,"");
        ActivityV3SuccessBinding binding = ActivityV3SuccessBinding.bind(mView);
        V3SuccessViewModel viewModel = new V3SuccessViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
