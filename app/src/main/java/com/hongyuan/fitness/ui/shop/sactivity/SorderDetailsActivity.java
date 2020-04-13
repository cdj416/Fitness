package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomSorderDetailsBinding;
import com.hongyuan.fitness.databinding.ActivitySOrderDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SorderDetailViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.SorderBottomViewModel;

public class SorderDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_s_order_details;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_sorder_details;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"订单详情");

        ActivitySOrderDetailsBinding binding = ActivitySOrderDetailsBinding.bind(mView);
        SorderDetailViewModel viewModel = new SorderDetailViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomSorderDetailsBinding bottomBinding = ActivityBottomSorderDetailsBinding.bind(bottomChildView);
        SorderBottomViewModel bottomViewModel = new SorderBottomViewModel(this,bottomBinding);
        bottomBinding.setViewModel(bottomViewModel);
    }
}
