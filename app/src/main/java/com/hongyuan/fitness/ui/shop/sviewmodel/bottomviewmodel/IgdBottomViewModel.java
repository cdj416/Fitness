package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomIntegralDetailsBinding;

public class IgdBottomViewModel extends CustomViewModel {

    private ActivityBottomIntegralDetailsBinding binding;

    public IgdBottomViewModel(CustomActivity mActivity, ActivityBottomIntegralDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
