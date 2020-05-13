package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopMessageBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopMessageViewModel;

public class ShopMessageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_message;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"我的消息");

        ActivityShopMessageBinding binding = ActivityShopMessageBinding.bind(mView);
        ShopMessageViewModel viewModel = new ShopMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
