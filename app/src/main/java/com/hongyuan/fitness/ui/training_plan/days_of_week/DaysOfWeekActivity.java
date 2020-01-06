package com.hongyuan.fitness.ui.training_plan.days_of_week;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityDaysOfWeekBinding;

public class DaysOfWeekActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_days_of_week;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityDaysOfWeekBinding binding = ActivityDaysOfWeekBinding.bind(mView);
        DaysWeekViewModel viewModel = new DaysWeekViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
