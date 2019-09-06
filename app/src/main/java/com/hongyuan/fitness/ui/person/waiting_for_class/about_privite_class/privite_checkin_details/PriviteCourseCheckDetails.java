package com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.privite_checkin_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivtiyPriviteCourseSiginDetailsBinding;

public class PriviteCourseCheckDetails extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activtiy_privite_course_sigin_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivtiyPriviteCourseSiginDetailsBinding binding = ActivtiyPriviteCourseSiginDetailsBinding.bind(mView);
        PriviteCourseCheckDetailsViewModel viewModel = new PriviteCourseCheckDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
