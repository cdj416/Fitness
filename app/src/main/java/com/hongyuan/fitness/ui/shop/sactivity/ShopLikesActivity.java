package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopLikesBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopLikesViewModel;

public class ShopLikesActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_likes;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"相似商品");

        ActivityShopLikesBinding binding = ActivityShopLikesBinding.bind(mView);
        ShopLikesViewModel viewModel = new ShopLikesViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
