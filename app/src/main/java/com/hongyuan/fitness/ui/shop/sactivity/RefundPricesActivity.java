package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityRefundPricesBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RefundPricesViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class RefundPricesActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_refund_prices;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"退款申请");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"退款申请");

        AcitivityRefundPricesBinding binding = AcitivityRefundPricesBinding.bind(mView);
        RefundPricesViewModel viewModel = new RefundPricesViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
