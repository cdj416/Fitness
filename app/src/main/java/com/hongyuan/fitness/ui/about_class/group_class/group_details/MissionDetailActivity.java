package com.hongyuan.fitness.ui.about_class.group_class.group_details;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMissionDetailsBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MissionDetailActivity extends CustomActivity {

    private MissionDetailViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mission_details;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();

        ActivityMissionDetailsBinding binding = ActivityMissionDetailsBinding.bind(mView);
        viewModel = new MissionDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    /*
     * 刷新数据
     * */
    @Subscribe(id = ConstantsCode.EB_SUPER_COURSE_XY_QD)
    public void refresh(String message) {
        viewModel.courseQD();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.closeDownTime();
        EventBus.getDefault().unregister(this);
    }
}
