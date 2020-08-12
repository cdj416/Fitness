package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCancelCourseBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CancelCourseActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_cancel_course;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"取消上课");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"取消上课");

        AcitivityCancelCourseBinding binding = AcitivityCancelCourseBinding.bind(mView);
        CanncelCourseViewModel viewModel = new CanncelCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
