package com.hongyuan.fitness.ui.person.mine_message;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineMessageBinding;

public class MineMessageActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initView() {
        setTitle("我的消息");
        setsetImmersive(0x55000000);
        ActivityMineMessageBinding binding = ActivityMineMessageBinding.bind(mView);
        MineMessageViewModel viewModel = new MineMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
