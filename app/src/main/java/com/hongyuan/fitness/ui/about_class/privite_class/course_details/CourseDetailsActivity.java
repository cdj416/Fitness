package com.hongyuan.fitness.ui.about_class.privite_class.course_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivtiyCourseDetailsBinding;

public class CourseDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activtiy_course_details;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivtiyCourseDetailsBinding binding = ActivtiyCourseDetailsBinding.bind(mView);
        CourseDetailsViewModel viewModel = new CourseDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
