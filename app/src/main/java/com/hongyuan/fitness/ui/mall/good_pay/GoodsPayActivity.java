package com.hongyuan.fitness.ui.mall.good_pay;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGoodsPayBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class GoodsPayActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_pay;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"订单支付");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"订单支付");

        ActivityGoodsPayBinding binding = ActivityGoodsPayBinding.bind(mView);
        GoodsPayViewModel viewModel = new GoodsPayViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
