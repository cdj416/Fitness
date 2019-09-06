package com.hongyuan.fitness.ui.person.waiting_for_class;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWaitingForClassBinding;

public class WaitingForClassViewModel extends CustomViewModel {

    private ActivityWaitingForClassBinding binding;

    public WaitingForClassViewModel(CustomActivity mActivity, ActivityWaitingForClassBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        WaitingForClassViewPagerAdapter meunAdapter = new WaitingForClassViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
