package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandDeviceSleepBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class WristbandSleepActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_device_sleep;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"睡眠");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"睡眠");

        ActivityWristbandDeviceSleepBinding binding = ActivityWristbandDeviceSleepBinding.bind(mView);
        WristbandSleepViewModel viewModel = new WristbandSleepViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
