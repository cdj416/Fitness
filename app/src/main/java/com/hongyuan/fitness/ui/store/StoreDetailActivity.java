package com.hongyuan.fitness.ui.store;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreDetailBinding;

public class StoreDetailActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    @Override
    protected void initView() {
        setTitle("门店主页");
        setsetImmersive(0x55000000);
        ActivityStoreDetailBinding binding = ActivityStoreDetailBinding.bind(mView);
        StoreDetailViewModel viewModel = new StoreDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
