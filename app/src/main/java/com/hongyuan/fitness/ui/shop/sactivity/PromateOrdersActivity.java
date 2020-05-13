package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromateOrdersBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.PromateOrdersViewModel;

public class PromateOrdersActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promate_orders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"推广订单");

        ActivityPromateOrdersBinding binding = ActivityPromateOrdersBinding.bind(mView);
        PromateOrdersViewModel viewModel = new PromateOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
