package com.hongyuan.fitness.ui.startup_page;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStartupPageBinding;

public class StartupPageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup_page;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivityStartupPageBinding binding = ActivityStartupPageBinding.bind(mView);
        StartupPageVeiwModel veiwModel = new StartupPageVeiwModel(this,binding);
        binding.setViewModel(veiwModel);
    }
}
