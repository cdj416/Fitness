package com.hongyuan.fitness.ui.person.my_coupon.select_coupon;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySelectCouponBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class SelectCouponActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_coupon;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"选择优惠券");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"选择优惠券");

        ActivitySelectCouponBinding binding = ActivitySelectCouponBinding.bind(mView);
        SelectCouponViewModel viewModel = new SelectCouponViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
