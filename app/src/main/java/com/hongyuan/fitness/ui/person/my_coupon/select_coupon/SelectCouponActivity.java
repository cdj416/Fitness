package com.hongyuan.fitness.ui.person.my_coupon.select_coupon;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySelectCouponBinding;

public class SelectCouponActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_coupon;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"选择优惠券");
        ActivitySelectCouponBinding binding = ActivitySelectCouponBinding.bind(mView);
        SelectCouponViewModel viewModel = new SelectCouponViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
