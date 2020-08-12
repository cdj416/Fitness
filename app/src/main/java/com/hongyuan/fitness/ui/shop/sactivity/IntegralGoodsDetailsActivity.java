package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomIntegralDetailsBinding;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IgdViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.IgdBottomViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class IntegralGoodsDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_goods_details;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_integral_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"积分商品");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"积分商品");

        ActivityIntegralGoodsDetailsBinding binding = ActivityIntegralGoodsDetailsBinding.bind(mView);
        IgdViewModel viewModel = new IgdViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomIntegralDetailsBinding bottomBinding = ActivityBottomIntegralDetailsBinding.bind(bottomChildView);
        IgdBottomViewModel bottomViewModel = new IgdBottomViewModel(this,bottomBinding,viewModel);
        bottomBinding.setViewModel(bottomViewModel);

    }
}
