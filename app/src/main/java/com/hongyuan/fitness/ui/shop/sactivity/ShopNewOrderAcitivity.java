package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopNewordersBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopNewOrderViewModel;

public class ShopNewOrderAcitivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_neworders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"商城订单");

        ActivityShopNewordersBinding binding = ActivityShopNewordersBinding.bind(mView);
        ShopNewOrderViewModel viewModel = new ShopNewOrderViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
