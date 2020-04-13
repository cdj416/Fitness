package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopStoreBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SstoreViewPagerAdapter;

public class SstoreViewModel extends CustomViewModel {

    private ActivityShopStoreBinding binding;

    public SstoreViewModel(CustomActivity mActivity, ActivityShopStoreBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        SstoreViewPagerAdapter pageApter = new SstoreViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(pageApter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
