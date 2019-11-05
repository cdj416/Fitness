package com.hongyuan.fitness.ui.person.my_coupon;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyCouponBinding;

public class MyCouponViewModel extends CustomViewModel {
    private ActivityMyCouponBinding binding;

    public MyCouponViewModel(CustomActivity mActivity, ActivityMyCouponBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        MyCouponPagerAdapter meunAdapter = new MyCouponPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
        binding.mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSuccess(Object data) {

    }
}
