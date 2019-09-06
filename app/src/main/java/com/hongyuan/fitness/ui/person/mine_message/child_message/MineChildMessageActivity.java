package com.hongyuan.fitness.ui.person.mine_message.child_message;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineChildMessageBinding;

public class MineChildMessageActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_child_message;
    }

    @Override
    protected void initView() {
        ActivityMineChildMessageBinding binding = ActivityMineChildMessageBinding.bind(mView);
        MineChildMessageViewModel viewModel = new MineChildMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
