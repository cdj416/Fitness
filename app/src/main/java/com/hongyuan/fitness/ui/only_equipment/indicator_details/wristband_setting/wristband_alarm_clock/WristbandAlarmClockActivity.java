package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandAlarmClockBinding;

public class WristbandAlarmClockActivity extends CustomActivity {

    private WristbandAlarmClockViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_alarm_clock;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_soid_ffef5b48,"闹钟");
        ActivityWristbandAlarmClockBinding binding = ActivityWristbandAlarmClockBinding.bind(mView);
        viewModel = new WristbandAlarmClockViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
