package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPlatformCouponsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.PlatformCouponsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class PlatformCouponsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_platform_coupons;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"领券中心");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"领券中心");

        ActivityPlatformCouponsBinding binding = ActivityPlatformCouponsBinding.bind(mView);
        PlatformCouponsViewModel viewModel = new PlatformCouponsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
