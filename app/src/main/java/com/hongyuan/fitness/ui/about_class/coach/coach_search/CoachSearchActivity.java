package com.hongyuan.fitness.ui.about_class.coach.coach_search;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SearchCoachBinding;

public class CoachSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_search_coach;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
        ActivityV3SearchCoachBinding binding = ActivityV3SearchCoachBinding.bind(mView);
        CoachSearchViewModel viewModel = new CoachSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
