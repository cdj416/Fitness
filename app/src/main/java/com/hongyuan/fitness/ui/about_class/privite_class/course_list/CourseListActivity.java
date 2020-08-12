package com.hongyuan.fitness.ui.about_class.privite_class.course_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCourseListBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CourseListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_list;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"课程列表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"课程列表");

        ActivityCourseListBinding binding = ActivityCourseListBinding.bind(mView);
        CouseListViewModel viewModel = new CouseListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
