package com.hongyuan.fitness.ui.person.my_coupon.newcoupon;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityNewMycouponBinding;

public class NewCouponActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_mycoupon;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"我的优惠券");

        ActivityNewMycouponBinding binding = ActivityNewMycouponBinding.bind(mView);
        NewCouponViewModel viewModel = new NewCouponViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
