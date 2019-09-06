package com.hongyuan.fitness.ui.person.setting;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySettingBinding;

public class SettingActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setTitle("设置");
        setsetImmersive(0x55000000);
        ActivitySettingBinding binding = ActivitySettingBinding.bind(mView);
        SettingViewModel viewModel = new SettingViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
