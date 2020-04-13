package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.view.View;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomSorderDetailsBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SorderPaySuccessActivity;

public class SorderBottomViewModel extends CustomViewModel {

    private ActivityBottomSorderDetailsBinding binding;

    public SorderBottomViewModel(CustomActivity mActivity, ActivityBottomSorderDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {
            startActivity(SorderPaySuccessActivity.class,null);
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
