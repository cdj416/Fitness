package com.hongyuan.fitness.ui.about_class.class_success;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityClassSuccessBinding;

public class SuccessClassActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_class_success;
    }

    @Override
    protected void initView() {
        setsetImmersive(0x55000000);
        getMainTitle().hideLine();
        ActivityClassSuccessBinding binding = ActivityClassSuccessBinding.bind(mView);
        SuccessClassViewModel viewModel = new SuccessClassViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
