package com.hongyuan.fitness.ui.training_plan.plan_program;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPlansProgramBinding;

public class PlansProgramActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plans_program;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"计划方案");
        ActivityPlansProgramBinding binding = ActivityPlansProgramBinding.bind(mView);
        PlansProgramViewModel viewModel = new PlansProgramViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
