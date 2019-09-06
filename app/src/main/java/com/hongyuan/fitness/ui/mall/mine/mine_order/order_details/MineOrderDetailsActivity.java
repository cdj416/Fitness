package com.hongyuan.fitness.ui.mall.mine.mine_order.order_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineOrderDetailBinding;

public class MineOrderDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_order_detail;
    }

    @Override
    protected void initView() {
        setTitle("订单详情");
        getMainTitle().showLine();
        setsetImmersive(0x55000000);
        ActivityMineOrderDetailBinding binding = ActivityMineOrderDetailBinding.bind(mView);
        MineOrderDetailsViewModel viewModel = new MineOrderDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
