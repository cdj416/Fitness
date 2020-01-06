package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySmartHistoricalDataBinding;

public class SmartLineChartActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_historical_data;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"历史数据");
        ActivitySmartHistoricalDataBinding binding = ActivitySmartHistoricalDataBinding.bind(mView);
        SmartLineChartViewModel viewModel = new SmartLineChartViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
