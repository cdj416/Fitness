package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomRefundGoodsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RefundGoodsViewModel;

public class BottomRefundGoodsViewModel extends CustomViewModel {

    private ActivityBottomRefundGoodsBinding binding;

    private RefundGoodsViewModel viewModel;

    public BottomRefundGoodsViewModel(CustomActivity mActivity, ActivityBottomRefundGoodsBinding binding, RefundGoodsViewModel viewModel) {
        super(mActivity);
        this.binding = binding;
        this.viewModel = viewModel;

        initView();
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {

            if(viewModel.isUpdateImg()){
                viewModel.updataFile();
            }else{
                viewModel.applyReturn("");
            }
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
