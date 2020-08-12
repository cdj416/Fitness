package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPayOrderDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class PayOrderDetailActivity extends CustomActivity {

    private PayOrderDetailViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_order_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"订单详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"订单详情");

        ActivityPayOrderDetailsBinding binding = ActivityPayOrderDetailsBinding.bind(mView);
        viewModel = new PayOrderDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
