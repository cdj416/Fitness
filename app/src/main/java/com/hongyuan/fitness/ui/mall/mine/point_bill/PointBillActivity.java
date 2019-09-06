package com.hongyuan.fitness.ui.mall.mine.point_bill;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPointBillBinding;

public class PointBillActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_point_bill;
    }

    @Override
    protected void initView() {
        setTitle("积分账单");
        setsetImmersive(0x55000000);

        ActivityPointBillBinding billBinding = ActivityPointBillBinding.bind(mView);
        PointBillViewModel viewModel = new PointBillViewModel(this,billBinding);
        billBinding.setViewModel(viewModel);
    }
}
