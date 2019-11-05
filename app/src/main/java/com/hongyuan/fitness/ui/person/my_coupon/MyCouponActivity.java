package com.hongyuan.fitness.ui.person.my_coupon;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyCouponBinding;

public class MyCouponActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"我的优惠券");
        ActivityMyCouponBinding binding = ActivityMyCouponBinding.bind(mView);
        MyCouponViewModel viewModel = new MyCouponViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
