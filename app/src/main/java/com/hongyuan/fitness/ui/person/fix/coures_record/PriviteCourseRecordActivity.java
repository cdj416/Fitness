package com.hongyuan.fitness.ui.person.fix.coures_record;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPersonCourseRecordBinding;

public class PriviteCourseRecordActivity extends CustomActivity {

    private PriviteCourseRecordViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_course_record;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"上课记录");

        ActivityPersonCourseRecordBinding binding = ActivityPersonCourseRecordBinding.bind(mView);
        viewModel = new PriviteCourseRecordViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
