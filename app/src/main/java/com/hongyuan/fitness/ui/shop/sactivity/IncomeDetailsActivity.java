package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityIncomeDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IncomeDetailsViewModel;

public class IncomeDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_income_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"结算订单明细");

        ActivityIncomeDetailsBinding binding = ActivityIncomeDetailsBinding.bind(mView);
        IncomeDetailsViewModel viewModel = new IncomeDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
