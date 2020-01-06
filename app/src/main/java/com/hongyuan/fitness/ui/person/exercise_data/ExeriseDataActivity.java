package com.hongyuan.fitness.ui.person.exercise_data;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityExerciseDataBinding;

public class ExeriseDataActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exercise_data;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_soid_fff27863,"运动数据");
        ActivityExerciseDataBinding binding = ActivityExerciseDataBinding.bind(mView);
        ExeriseDataViewModel viewModel = new ExeriseDataViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
