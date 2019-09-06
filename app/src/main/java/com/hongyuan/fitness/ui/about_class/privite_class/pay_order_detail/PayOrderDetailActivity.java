package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPayOrderDetailsBinding;

public class PayOrderDetailActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_order_details;
    }

    @Override
    protected void initView() {
        setTitle("订单详情");
        setsetImmersive(0x55000000);
        ActivityPayOrderDetailsBinding binding = ActivityPayOrderDetailsBinding.bind(mView);
        PayOrderDetailViewModel viewModel = new PayOrderDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
