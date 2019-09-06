package com.hongyuan.fitness.ui.about_class.coach.coach_profile;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCoachProfileBinding;


public class CoachProfileActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_profile;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivityCoachProfileBinding binding = ActivityCoachProfileBinding.bind(mView);
        CoachProfileViewModel viewModel = new CoachProfileViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
