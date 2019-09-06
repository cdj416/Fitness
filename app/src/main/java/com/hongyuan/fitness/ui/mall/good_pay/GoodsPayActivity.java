package com.hongyuan.fitness.ui.mall.good_pay;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGoodsPayBinding;

public class GoodsPayActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_pay;
    }

    @Override
    protected void initView() {
        setTitle("订单支付");
        setsetImmersive(0x55000000);
        ActivityGoodsPayBinding binding = ActivityGoodsPayBinding.bind(mView);
        GoodsPayViewModel viewModel = new GoodsPayViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
