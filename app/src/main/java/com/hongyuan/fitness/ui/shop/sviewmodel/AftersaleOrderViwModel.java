package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAftersaleOrderBinding;
import com.hongyuan.fitness.ui.shop.sactivity.AfterSalesActivity;
import com.hongyuan.fitness.ui.shop.sactivity.LogisticsActivity;

public class AftersaleOrderViwModel extends CustomViewModel {

    private ActivityAftersaleOrderBinding binding;

    public AftersaleOrderViwModel(CustomActivity mActivity, ActivityAftersaleOrderBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        binding.goLogistics.setOnClickListener(v -> {
            startActivity(LogisticsActivity.class,null);
        });
        binding.goAfterSales.setOnClickListener(v ->
                startActivity(AfterSalesActivity.class,null));
    }

    @Override
    public void onSuccess(Object data) {

    }
}
