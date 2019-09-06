package com.hongyuan.fitness.ui.about_class.coach.coach_homepage;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCoachHomepageBinding;

public class CoachHomePageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_homepage;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");
        ActivityCoachHomepageBinding binding = ActivityCoachHomepageBinding.bind(mView);
        CoachHomePageViewModel viewModel = new CoachHomePageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
