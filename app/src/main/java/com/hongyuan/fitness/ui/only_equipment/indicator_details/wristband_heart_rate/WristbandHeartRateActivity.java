package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_heart_rate;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityHeartRateBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class WristbandHeartRateActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heart_rate;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_soid_ffff8993,"心率");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"心率");

        ActivityHeartRateBinding binding = ActivityHeartRateBinding.bind(mView);
        WristbandHeartRateViewModel viewModel = new WristbandHeartRateViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
