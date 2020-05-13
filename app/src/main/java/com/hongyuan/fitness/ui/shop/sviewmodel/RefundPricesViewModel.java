package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityRefundPricesBinding;

public class RefundPricesViewModel extends CustomViewModel {

    private AcitivityRefundPricesBinding binding;

    public RefundPricesViewModel(CustomActivity mActivity, AcitivityRefundPricesBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
