package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCollectCouponsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CollectCouponsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class CollectCouponsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_coupons;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"领券中心");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"领券中心");

        ActivityCollectCouponsBinding binding = ActivityCollectCouponsBinding.bind(mView);
        CollectCouponsViewModel viewModel = new CollectCouponsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
