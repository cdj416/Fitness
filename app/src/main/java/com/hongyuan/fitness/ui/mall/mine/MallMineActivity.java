package com.hongyuan.fitness.ui.mall.mine;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMallMineBinding;

public class MallMineActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_mine;
    }

    @Override
    protected void initView() {
        setTypeBar("我的",R.drawable.shape_gradient_h_39_4a);
        ActivityMallMineBinding binding = ActivityMallMineBinding.bind(mView);
        MallMineViewModel viewModel = new MallMineViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
