package com.hongyuan.fitness.ui.about_class.check_payment_method;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCheckPaymentMethodBinding;

public class CheckPayMethodActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_payment_method;
    }

    @Override
    protected void initView() {
        setTitle("订单详情");
        setsetImmersive(0x55000000);
        ActivityCheckPaymentMethodBinding binding = ActivityCheckPaymentMethodBinding.bind(mView);
        CheckPayMethodViewModel viewModel = new CheckPayMethodViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
