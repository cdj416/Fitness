package com.hongyuan.fitness.ui.encyclopedia.select_encyclopedia_type;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaTypeBinding;

public class EncyclopediaTypeActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_encyclopedia_type;
    }

    @Override
    protected void initView() {
        setTitle("选择分类");
        setsetImmersive(0x55000000);
        ActivityEncyclopediaTypeBinding binding = ActivityEncyclopediaTypeBinding.bind(mView);
        EncyclopediaTypeViewModel viewModel = new EncyclopediaTypeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
