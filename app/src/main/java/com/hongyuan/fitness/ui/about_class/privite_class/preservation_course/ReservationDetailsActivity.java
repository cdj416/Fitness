package com.hongyuan.fitness.ui.about_class.privite_class.preservation_course;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPriviteCourseReservationDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class ReservationDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privite_course_reservation_details;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"预约上课");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"预约上课");

        ActivityPriviteCourseReservationDetailsBinding binding = ActivityPriviteCourseReservationDetailsBinding.bind(mView);
        ReservationDetailsViewModel viewModel = new ReservationDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
