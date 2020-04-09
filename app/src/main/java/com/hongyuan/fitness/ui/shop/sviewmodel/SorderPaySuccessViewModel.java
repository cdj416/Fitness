package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySOrderPaySuccessBinding;

public class SorderPaySuccessViewModel extends CustomViewModel {

    private ActivitySOrderPaySuccessBinding binding;

    public SorderPaySuccessViewModel(CustomActivity mActivity,ActivitySOrderPaySuccessBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
