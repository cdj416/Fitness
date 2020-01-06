package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.add_alarm_clock;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAddWristbandAlarmClockBinding;

public class WristbandAddClockActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_wristband_alarm_clock;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"设置时间");
        ActivityAddWristbandAlarmClockBinding binding = ActivityAddWristbandAlarmClockBinding.bind(mView);
        WristbandAddClockViewModel viewModel = new WristbandAddClockViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
