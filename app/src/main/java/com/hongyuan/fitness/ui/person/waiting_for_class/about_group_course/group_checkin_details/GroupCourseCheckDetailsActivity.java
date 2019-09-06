package com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.group_checkin_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGroupcourseCheckDetailsBinding;

public class GroupCourseCheckDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_groupcourse_check_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivityGroupcourseCheckDetailsBinding binding = ActivityGroupcourseCheckDetailsBinding.bind(mView);
        GroupCourseCheckDetailsViewModel viewModel = new GroupCourseCheckDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
