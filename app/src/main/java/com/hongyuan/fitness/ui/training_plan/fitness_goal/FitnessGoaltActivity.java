package com.hongyuan.fitness.ui.training_plan.fitness_goal;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFitnessGoalBinding;

public class FitnessGoaltActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fitness_goal;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityFitnessGoalBinding binding = ActivityFitnessGoalBinding.bind(mView);
        FitnessGoaltViewModel viewModel = new FitnessGoaltViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
