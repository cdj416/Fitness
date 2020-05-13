package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopCollectBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.ScollectPagerAdapter;

public class ShopCollectViewModel extends CustomViewModel {

    private ActivityShopCollectBinding binding;

    public ShopCollectViewModel(CustomActivity mActivity, ActivityShopCollectBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        ScollectPagerAdapter meunAdapter = new ScollectPagerAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(4);

    }

    @Override
    public void onSuccess(Object data) {

    }
}
