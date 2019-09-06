package com.hongyuan.fitness.ui.about_class.class_failure;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFailureBinding;

public class FailureActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_failure;
    }

    @Override
    protected void initView() {
        setsetImmersive(0x55000000);
        ActivityFailureBinding binding = ActivityFailureBinding.bind(mView);
        FailureViewModel viewModel = new FailureViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
