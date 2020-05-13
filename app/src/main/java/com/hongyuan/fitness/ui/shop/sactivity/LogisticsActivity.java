package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityLogisticsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.LogisticsViewModel;

public class LogisticsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"物流详情");

        ActivityLogisticsBinding binding = ActivityLogisticsBinding.bind(mView);
        LogisticsViewModel viewModel = new LogisticsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
