package com.hongyuan.fitness.ui.training_plan.target_weight;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityTargetWeightBinding;

public class TargetWeightActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_target_weight;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityTargetWeightBinding binding = ActivityTargetWeightBinding.bind(mView);
        TargetWeightViewModel viewModel = new TargetWeightViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
