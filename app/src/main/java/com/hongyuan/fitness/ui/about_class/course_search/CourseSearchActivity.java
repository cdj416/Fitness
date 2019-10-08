package com.hongyuan.fitness.ui.about_class.course_search;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SearchCourseBinding;

public class CourseSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_search_course;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
        ActivityV3SearchCourseBinding binding = ActivityV3SearchCourseBinding.bind(mView);
        CourseSearchViewModel viewModel = new CourseSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
