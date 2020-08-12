package com.hongyuan.fitness.ui.person.my_coupon.newcoupon;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityNewMycouponBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class NewCouponActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_mycoupon;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的优惠券");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的优惠券");

        ActivityNewMycouponBinding binding = ActivityNewMycouponBinding.bind(mView);
        NewCouponViewModel viewModel = new NewCouponViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
