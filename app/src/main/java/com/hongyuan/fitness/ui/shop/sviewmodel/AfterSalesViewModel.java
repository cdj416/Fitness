package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAfterSalesBinding;

public class AfterSalesViewModel extends CustomViewModel {

    private ActivityAfterSalesBinding binding;

    public AfterSalesViewModel(CustomActivity mActivity, ActivityAfterSalesBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
