package com.hongyuan.fitness.ui.person.fix;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySixPriviteCourseBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class SixPriviteCourseActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_six_privite_course;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的私教课");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的私教课");

        ActivitySixPriviteCourseBinding binding = ActivitySixPriviteCourseBinding.bind(mView);
        SixPriviteCourseViewModel viewModel = new SixPriviteCourseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
