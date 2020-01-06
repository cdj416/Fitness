package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting;

import android.view.View;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWristbandSettingBinding;

public class WristbandSettingActivity extends CustomActivity {

    private WristbandSettingViwModel viwModel;

    private TextView button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wristband_setting;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.view_bottom_wristband_setting;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"设置");

        ActivityWristbandSettingBinding binding = ActivityWristbandSettingBinding.bind(mView);
        viwModel = new WristbandSettingViwModel(this,binding);
        binding.setViewModel(viwModel);
    }

    @Override
    protected void onResume() {
        super.onResume();

        viwModel.refreshData();
    }

    @Override
    protected void initBottomView(View bottomChildView) {
        button = bottomChildView.findViewById(R.id.button);

        button.setOnClickListener(v -> {
            //解除设备的绑定
            viwModel.unBind();
        });
    }
}
