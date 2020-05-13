package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityRefundPricesBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RefundPricesViewModel;

public class RefundPricesActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_refund_prices;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"退款申请");

        AcitivityRefundPricesBinding binding = AcitivityRefundPricesBinding.bind(mView);
        RefundPricesViewModel viewModel = new RefundPricesViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
