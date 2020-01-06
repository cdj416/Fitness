package com.hongyuan.fitness.ui.training_plan;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityTrainingPlanBinding;

public class TrainingPlanActivity extends CustomActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_training_plan;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityTrainingPlanBinding binding = ActivityTrainingPlanBinding.bind(mView);
        TrainingPlanViewModel viewModel = new TrainingPlanViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
