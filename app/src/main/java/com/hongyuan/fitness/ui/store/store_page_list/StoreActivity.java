package com.hongyuan.fitness.ui.store.store_page_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreBinding;

public class StoreActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"门店列表");
        ActivityStoreBinding binding = ActivityStoreBinding.bind(mView);
        StoreViewModel viewModel = new StoreViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
