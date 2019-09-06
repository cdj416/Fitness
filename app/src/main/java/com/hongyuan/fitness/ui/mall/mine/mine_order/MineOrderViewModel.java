package com.hongyuan.fitness.ui.mall.mine.mine_order;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMineOrderBinding;

public class MineOrderViewModel extends CustomViewModel {

    private ActivityMineOrderBinding binding;
    private MineOrderPagerAdapter viewPagerAdapter;

    public MineOrderViewModel(CustomActivity mActivity, ActivityMineOrderBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        viewPagerAdapter = new MineOrderPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(viewPagerAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(5);
    }



    @Override
    public void onSuccess(Object data) {

    }
}
