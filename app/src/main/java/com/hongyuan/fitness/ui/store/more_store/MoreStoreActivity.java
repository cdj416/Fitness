package com.hongyuan.fitness.ui.store.more_store;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMoreStoreBinding;

public class MoreStoreActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_store;
    }

    @Override
    protected void initView() {
        setTitle("更多门店");
        setsetImmersive(0x55000000);
        ActivityMoreStoreBinding binding = ActivityMoreStoreBinding.bind(mView);
        MoreStoreViewModel viewModel = new MoreStoreViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
