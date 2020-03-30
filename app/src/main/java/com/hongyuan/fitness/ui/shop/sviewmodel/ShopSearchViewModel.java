package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopSearchBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SsearchShopPagerAdapter;

public class ShopSearchViewModel extends CustomViewModel {

    private ActivityShopSearchBinding binding;

    public ShopSearchViewModel(CustomActivity mActivity, ActivityShopSearchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        SsearchShopPagerAdapter meunAdapter = new SsearchShopPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
