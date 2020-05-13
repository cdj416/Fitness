package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;

public class AfterSalesActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_after_sales;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"退款/售后");
    }
}
