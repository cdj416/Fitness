package com.hongyuan.fitness.ui.carousel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCarouselBinding;

public class CarouseViewModel extends CustomViewModel {
    private ActivityCarouselBinding binding;

    public CarouseViewModel(CustomActivity mActivity, ActivityCarouselBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        CarousePagerAdapter meunAdapter = new CarousePagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
        binding.mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
