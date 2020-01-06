package com.hongyuan.fitness.ui.training_plan.basic_information;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBasicInformationBinding;

public class BasicInformationActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_basic_information;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"训练计划");
        ActivityBasicInformationBinding binding = ActivityBasicInformationBinding.bind(mView);
        BasicInformationViewModel viewModel = new BasicInformationViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
