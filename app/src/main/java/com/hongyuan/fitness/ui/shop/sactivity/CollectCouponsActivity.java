package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCollectCouponsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CollectCouponsViewModel;

public class CollectCouponsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_coupons;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"领券中心");

        ActivityCollectCouponsBinding binding = ActivityCollectCouponsBinding.bind(mView);
        CollectCouponsViewModel viewModel = new CollectCouponsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
