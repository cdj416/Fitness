package com.hongyuan.fitness.ui.encyclopedia;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaBinding;

public class EncyclopediaActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encyclopedia;
    }

    @Override
    protected void initView() {
        setTitle("健康百科");
        setsetImmersive(0x55000000);
        ActivityEncyclopediaBinding binding =ActivityEncyclopediaBinding.bind(mView);
        EncyclopediaViewModel viewModel = new EncyclopediaViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
