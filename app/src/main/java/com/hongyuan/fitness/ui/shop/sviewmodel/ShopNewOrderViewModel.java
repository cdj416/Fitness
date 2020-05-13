package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopNewordersBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SnewOrderViwPageAdapter;

public class ShopNewOrderViewModel extends CustomViewModel {

    private ActivityShopNewordersBinding binding;

    public ShopNewOrderViewModel(CustomActivity mActivity, ActivityShopNewordersBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        SnewOrderViwPageAdapter meunAdapter = new SnewOrderViwPageAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(6);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
