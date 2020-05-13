package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAftersaleOrderBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.AftersaleOrderViwModel;

public class AftersaleOrderActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aftersale_order;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"订单详情");

        ActivityAftersaleOrderBinding binding = ActivityAftersaleOrderBinding.bind(mView);
        AftersaleOrderViwModel viwModel = new AftersaleOrderViwModel(this,binding);
        binding.setViewModel(viwModel);
    }
}
