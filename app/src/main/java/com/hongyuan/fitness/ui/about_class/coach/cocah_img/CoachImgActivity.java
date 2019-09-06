package com.hongyuan.fitness.ui.about_class.coach.cocah_img;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCoachPhotoBinding;

public class CoachImgActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_photo;
    }

    @Override
    protected void initView() {
        setsetImmersive(0x55000000);
        ActivityCoachPhotoBinding binding = ActivityCoachPhotoBinding.bind(mView);
        CoachImgViewModel viewModel = new CoachImgViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
