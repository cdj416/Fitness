package com.hongyuan.fitness.ui.mall;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGoodBinding;

public class GoodActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_good;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setsetImmersive(0x55000000);
        ActivityGoodBinding binding = ActivityGoodBinding.bind(mView);
        GoodViewModel viewModel = new GoodViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
