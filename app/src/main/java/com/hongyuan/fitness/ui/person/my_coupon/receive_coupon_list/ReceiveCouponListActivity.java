package com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityReceiveCouponListBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class ReceiveCouponListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_coupon_list;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"优惠券");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"优惠券");

        ActivityReceiveCouponListBinding binding = ActivityReceiveCouponListBinding.bind(mView);
        ReceiveCouponListViewModel viewModel = new ReceiveCouponListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
