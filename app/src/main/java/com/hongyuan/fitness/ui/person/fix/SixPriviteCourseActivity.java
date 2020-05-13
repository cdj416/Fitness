package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySixPriviteCourseBinding;

public class SixPriviteCourseActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_six_privite_course;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"我的私教课");

        ActivitySixPriviteCourseBinding binding = ActivitySixPriviteCourseBinding.bind(mView);
        SixPriviteCourseViewModel viewModel = new SixPriviteCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
