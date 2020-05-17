package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityNewOrderBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.NewOrderViewModel;

public class NewOrderActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_order;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"");

        ActivityNewOrderBinding binding = ActivityNewOrderBinding.bind(mView);
        NewOrderViewModel viewModel = new NewOrderViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
