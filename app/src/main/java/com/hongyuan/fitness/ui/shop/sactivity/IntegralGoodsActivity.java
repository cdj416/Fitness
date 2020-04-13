package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IntegralGoddsViewModel;

public class IntegralGoodsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_goods;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"积分兑换");

        ActivityIntegralGoodsBinding binding = ActivityIntegralGoodsBinding.bind(mView);
        IntegralGoddsViewModel viewModel = new IntegralGoddsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
