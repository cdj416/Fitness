package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityCustomServerBinding;

public class CustomServerViewModel extends CustomViewModel {

    private AcitivityCustomServerBinding binding;

    public CustomServerViewModel(CustomActivity mActivity, AcitivityCustomServerBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
