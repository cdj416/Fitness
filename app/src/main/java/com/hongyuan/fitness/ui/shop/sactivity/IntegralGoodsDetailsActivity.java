package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomIntegralDetailsBinding;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IgdViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.IgdBottomViewModel;

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
        setTitleBar(TYPE_BAR7,R.drawable.shape_soid_ffffff,"");
        ActivityIntegralGoodsDetailsBinding binding = ActivityIntegralGoodsDetailsBinding.bind(mView);
        IgdViewModel viewModel = new IgdViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomIntegralDetailsBinding bottomBinding = ActivityBottomIntegralDetailsBinding.bind(bottomChildView);
        IgdBottomViewModel bottomViewModel = new IgdBottomViewModel(this,bottomBinding);
        bottomBinding.setViewModel(bottomViewModel);

    }
}
