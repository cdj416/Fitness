package com.hongyuan.fitness.ui.heat.heat_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFoodHeatDetailsBinding;

public class HeatDetailActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_heat_details;
    }

    @Override
    protected void initView() {
        setTitle("食物详情");
        setsetImmersive(0x55000000);
        ActivityFoodHeatDetailsBinding binding = ActivityFoodHeatDetailsBinding.bind(mView);
        HeatDetailViewModel viewModel = new HeatDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
