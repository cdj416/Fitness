package com.hongyuan.fitness.ui.about_class.group_class.group_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMissionDetailsBinding;

public class MissionDetailActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mission_details;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();

        ActivityMissionDetailsBinding binding = ActivityMissionDetailsBinding.bind(mView);
        MissionDetailViewModel viewModel = new MissionDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
