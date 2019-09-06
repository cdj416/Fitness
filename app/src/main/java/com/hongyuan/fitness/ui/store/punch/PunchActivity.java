package com.hongyuan.fitness.ui.store.punch;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreSignInBinding;

public class PunchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_sign_in;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivityStoreSignInBinding binding = ActivityStoreSignInBinding.bind(mView);
        PunchViewModel viewModel = new PunchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
