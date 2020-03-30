package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopSearchBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopSearchViewModel;

public class ShopSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_search;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");

        ActivityShopSearchBinding binding = ActivityShopSearchBinding.bind(mView);
        ShopSearchViewModel viewModel = new ShopSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
