package com.hongyuan.fitness.ui.about_class.coach.coach_search;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SearchCoachBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CoachSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_search_coach;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR8,R.drawable.theme_shape_soid_ffffff_black,"");

        ActivityV3SearchCoachBinding binding = ActivityV3SearchCoachBinding.bind(mView);
        CoachSearchViewModel viewModel = new CoachSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
