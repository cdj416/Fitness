package com.hongyuan.fitness.ui.person.my_collection;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyCollectionBinding;

public class MyCollectionViewModel extends CustomViewModel {
    private ActivityMyCollectionBinding binding;

    public MyCollectionViewModel(CustomActivity mActivity, ActivityMyCollectionBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        MyCollectionPagerAdapter meunAdapter = new MyCollectionPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
        binding.mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
