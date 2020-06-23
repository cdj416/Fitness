package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityMapBinding;

public class MapViewModel extends CustomViewModel {

    private AcitivityMapBinding binding;
    public MapViewModel(CustomActivity mActivity, AcitivityMapBinding binding) {
        super(mActivity);
        this.binding = binding;
    }



    @Override
    public void onSuccess(Object data) {

    }
}
