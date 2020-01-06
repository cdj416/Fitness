package com.hongyuan.fitness.ui.only_equipment.indicator_details;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityIndicatorDetailsBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.WristbandSettingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class IndicatorDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_indicator_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"智能设备");
        ActivityIndicatorDetailsBinding binding = ActivityIndicatorDetailsBinding.bind(mView);
        IndicatorDetailsViewModel viewModel = new IndicatorDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    /*
     * 显示设置图标
     * */
    @Subscribe(id = ConstantsCode.EB_WRISTBAND_SHOW_CONTENT)
    public void showSetting(String message) {
        getMainTitle().setRightImage(R.mipmap.person_setting_mark);
        getMainTitle().getRightView().setOnClickListener(v -> startActivity(WristbandSettingActivity.class,null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
