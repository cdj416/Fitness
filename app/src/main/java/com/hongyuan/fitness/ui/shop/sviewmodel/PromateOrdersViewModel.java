package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPromateOrdersBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.PromateOrdersViwPageAdapter;

public class PromateOrdersViewModel extends CustomViewModel {

    private ActivityPromateOrdersBinding binding;

    public PromateOrdersViewModel(CustomActivity mActivity, ActivityPromateOrdersBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        PromateOrdersViwPageAdapter meunAdapter = new PromateOrdersViwPageAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
