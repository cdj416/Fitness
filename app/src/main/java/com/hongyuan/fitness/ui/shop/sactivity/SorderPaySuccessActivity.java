package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomSOrderPaySuccessBinding;
import com.hongyuan.fitness.databinding.ActivitySOrderPaySuccessBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SorderPaySuccessViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.SorderBottomPaySuccessViewModel;

public class SorderPaySuccessActivity extends CustomActivity {

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_s_order_pay_success;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_s_order_pay_success;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"订单完成");

        ActivityBottomSOrderPaySuccessBinding bottomBinding = ActivityBottomSOrderPaySuccessBinding.bind(bottomChildView);
        SorderBottomPaySuccessViewModel bViewModel = new SorderBottomPaySuccessViewModel(this,bottomBinding);
        bottomBinding.setViewModel(bViewModel);

        ActivitySOrderPaySuccessBinding binding = ActivitySOrderPaySuccessBinding.bind(mView);
        SorderPaySuccessViewModel viewModel = new SorderPaySuccessViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
