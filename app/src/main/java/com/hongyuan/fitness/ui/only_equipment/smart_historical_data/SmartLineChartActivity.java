package com.hongyuan.fitness.ui.only_equipment.smart_historical_data;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySmartHistoricalDataBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class SmartLineChartActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_historical_data;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"历史数据");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"历史数据");

        ActivitySmartHistoricalDataBinding binding = ActivitySmartHistoricalDataBinding.bind(mView);
        SmartLineChartViewModel viewModel = new SmartLineChartViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
