package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_step;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandStepBinding;

public class WristbandStepActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_step;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"步数");
        ActivityWristbandStepBinding binding = ActivityWristbandStepBinding.bind(mView);
        WristbandStepViewModel viewModel = new WristbandStepViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
