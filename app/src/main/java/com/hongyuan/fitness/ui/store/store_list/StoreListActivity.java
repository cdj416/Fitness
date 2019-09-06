package com.hongyuan.fitness.ui.store.store_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreListBinding;

public class StoreListActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_list;
    }

    @Override
    protected void initView() {
        setTitle("门店主页");
        setsetImmersive(0x55000000);
        ActivityStoreListBinding binding = ActivityStoreListBinding.bind(mView);
        StoreListViewModel viewModel = new StoreListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }


}
