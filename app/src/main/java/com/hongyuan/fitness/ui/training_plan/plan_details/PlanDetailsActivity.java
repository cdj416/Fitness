package com.hongyuan.fitness.ui.training_plan.plan_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPlanDetailsBinding;

public class PlanDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plan_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityPlanDetailsBinding binding = ActivityPlanDetailsBinding.bind(mView);
        PlanDetailsViewModel viewModel = new PlanDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
