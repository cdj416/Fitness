package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopMenuBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopMenuViewModel;

public class ShopMenuActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_menu;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"商品分类");
        ActivityShopMenuBinding binding = ActivityShopMenuBinding.bind(mView);
        ShopMenuViewModel viewModel = new ShopMenuViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
