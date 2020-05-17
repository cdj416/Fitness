package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomIntegralDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IgdViewModel;

public class IgdBottomViewModel extends CustomViewModel {

    private ActivityBottomIntegralDetailsBinding binding;

    private IgdViewModel viewModel;

    public IgdBottomViewModel(CustomActivity mActivity, ActivityBottomIntegralDetailsBinding binding, IgdViewModel viewModel) {
        super(mActivity);
        this.binding = binding;
        this.viewModel = viewModel;
        initView();
    }

    @Override
    protected void initView() {

        //去兑换
        binding.goExchange.setOnClickListener(v -> {
            viewModel.showSpecification();
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
