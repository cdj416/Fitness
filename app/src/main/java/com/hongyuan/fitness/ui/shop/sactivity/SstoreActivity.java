package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopStoreBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SstoreViewModel;

public class SstoreActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_store;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"店铺主页");

        ActivityShopStoreBinding binding = ActivityShopStoreBinding.bind(mView);
        SstoreViewModel viewModel = new SstoreViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
