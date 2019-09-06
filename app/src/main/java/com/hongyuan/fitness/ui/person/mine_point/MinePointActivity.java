package com.hongyuan.fitness.ui.person.mine_point;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMinePointBinding;

public class MinePointActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_point;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"我的");
        ActivityMinePointBinding binding = ActivityMinePointBinding.bind(mView);
        MinePointViewModel viewModel = new MinePointViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
