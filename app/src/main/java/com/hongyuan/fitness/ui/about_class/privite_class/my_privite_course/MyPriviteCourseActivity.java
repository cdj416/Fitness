package com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyPriviteCourseBinding;

public class MyPriviteCourseActivity extends CustomActivity {

    private MyPriviteCourseViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_privite_course;
    }

    @Override
    protected void initView() {
        setTitle("待约课");
        setsetImmersive(0x55000000);
        ActivityMyPriviteCourseBinding binding = ActivityMyPriviteCourseBinding.bind(mView);
        viewModel = new MyPriviteCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
