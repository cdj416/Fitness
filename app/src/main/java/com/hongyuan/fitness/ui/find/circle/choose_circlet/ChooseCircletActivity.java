package com.hongyuan.fitness.ui.find.circle.choose_circlet;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityChooseCircletBinding;

public class ChooseCircletActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_circlet;
    }

    @Override
    protected void initView() {
        setTitle("更多圈子");
        setsetImmersive(0x55000000);
        ActivityChooseCircletBinding binding = ActivityChooseCircletBinding.bind(mView);
        ChooseCirletViewModel viewModel = new ChooseCirletViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
