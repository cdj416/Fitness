package com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityReceiveCouponListBinding;

public class ReceiveCouponListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_coupon_list;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,0,"优惠券");
        ActivityReceiveCouponListBinding binding = ActivityReceiveCouponListBinding.bind(mView);
        ReceiveCouponListViewModel viewModel = new ReceiveCouponListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
