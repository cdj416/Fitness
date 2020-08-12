package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_step;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandStepBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class WristbandStepActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_step;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"步数");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.shape_gradient_h_39_4a_black,"步数");

        ActivityWristbandStepBinding binding = ActivityWristbandStepBinding.bind(mView);
        WristbandStepViewModel viewModel = new WristbandStepViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
