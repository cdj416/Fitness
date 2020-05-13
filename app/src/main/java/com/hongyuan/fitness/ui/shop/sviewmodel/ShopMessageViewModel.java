package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopMessageBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SmessagePagerAdapter;

public class ShopMessageViewModel extends CustomViewModel {

    private ActivityShopMessageBinding binding;

    public ShopMessageViewModel(CustomActivity mActivity, ActivityShopMessageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        SmessagePagerAdapter meunAdapter = new SmessagePagerAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(4);

    }

    @Override
    public void onSuccess(Object data) {

    }
}
