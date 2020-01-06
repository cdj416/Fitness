package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandDeviceSleepBinding;

public class WristbandSleepActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_device_sleep;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_soil_ff8f8bff,"睡眠");
        ActivityWristbandDeviceSleepBinding binding = ActivityWristbandDeviceSleepBinding.bind(mView);
        WristbandSleepViewModel viewModel = new WristbandSleepViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
