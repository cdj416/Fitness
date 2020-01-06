package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_msg;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandMsgRemindBinding;

public class WristbandMsgActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_msg_remind;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"消息提醒");
        ActivityWristbandMsgRemindBinding binding = ActivityWristbandMsgRemindBinding.bind(mView);
        WristbandMsgViwModel viwModel = new WristbandMsgViwModel(this,binding);
        binding.setViewModel(viwModel);
    }
}
