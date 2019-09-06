package com.hongyuan.fitness.ui.person.waiting_for_class;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWaitingForClassBinding;

public class WaitingForClassActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_waiting_for_class;
    }

    @Override
    protected void initView() {
        setTitle("待上课");
        setsetImmersive(0x55000000);
        ActivityWaitingForClassBinding binding = ActivityWaitingForClassBinding.bind(mView);
        WaitingForClassViewModel viewModel = new WaitingForClassViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
