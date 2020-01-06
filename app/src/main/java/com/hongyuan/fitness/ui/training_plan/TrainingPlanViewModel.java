package com.hongyuan.fitness.ui.training_plan;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityTrainingPlanBinding;
import com.hongyuan.fitness.ui.training_plan.basic_information.BasicInformationActivity;

public class TrainingPlanViewModel extends CustomViewModel {


    private ActivityTrainingPlanBinding binding;

    public TrainingPlanViewModel(CustomActivity mActivity, ActivityTrainingPlanBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.goNext.setOnClickListener(v -> startActivity(BasicInformationActivity.class,null));
    }

    @Override
    public void onSuccess(Object data) {

    }
}
