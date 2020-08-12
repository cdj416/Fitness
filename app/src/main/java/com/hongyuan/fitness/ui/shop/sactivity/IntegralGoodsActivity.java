package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IntegralGoddsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class IntegralGoodsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_goods;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"积分兑换");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"积分兑换");

        ActivityIntegralGoodsBinding binding = ActivityIntegralGoodsBinding.bind(mView);
        IntegralGoddsViewModel viewModel = new IntegralGoddsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
