package com.hongyuan.fitness.ui.about_class.privite_class.course_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCourseListBinding;

public class CourseListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    protected void initView() {
        setTitle("课程列表");
        setsetImmersive(0x55000000);
        ActivityCourseListBinding binding = ActivityCourseListBinding.bind(mView);
        CouseListViewModel viewModel = new CouseListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
