package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCancelCourseBinding;

public class CancelCourseActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_cancel_course;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"取消上课");

        AcitivityCancelCourseBinding binding = AcitivityCancelCourseBinding.bind(mView);
        CanncelCourseViewModel viewModel = new CanncelCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
