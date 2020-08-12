package com.hongyuan.fitness.ui.about_class.coach.coach_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCoachListBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CoachListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_coach_list;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"私教列表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"私教列表");

        ActivityCoachListBinding binding = ActivityCoachListBinding.bind(mView);
        CoachListViewModel viewModel = new CoachListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
