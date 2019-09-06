package com.hongyuan.fitness.ui.about_class.privite_class.preservation_course;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPriviteCourseReservationDetailsBinding;

public class ReservationDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_privite_course_reservation_details;
    }

    @Override
    protected void initView() {
        setTitle("预约上课");
        setsetImmersive(0x55000000);
        ActivityPriviteCourseReservationDetailsBinding binding = ActivityPriviteCourseReservationDetailsBinding.bind(mView);
        ReservationDetailsViewModel viewModel = new ReservationDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
