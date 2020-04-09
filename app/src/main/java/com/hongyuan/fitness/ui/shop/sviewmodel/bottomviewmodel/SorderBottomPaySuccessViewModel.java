package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomSOrderPaySuccessBinding;

public class SorderBottomPaySuccessViewModel extends CustomViewModel {

    private ActivityBottomSOrderPaySuccessBinding bottomBinding;

    public SorderBottomPaySuccessViewModel(CustomActivity mActivity, ActivityBottomSOrderPaySuccessBinding bottomBinding) {
        super(mActivity);
        this.bottomBinding = bottomBinding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
