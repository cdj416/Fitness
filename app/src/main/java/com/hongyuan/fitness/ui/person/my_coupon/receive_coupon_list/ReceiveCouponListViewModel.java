package com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityReceiveCouponListBinding;
public class ReceiveCouponListViewModel extends CustomViewModel {

    private ActivityReceiveCouponListBinding binding;

    public ReceiveCouponListViewModel(CustomActivity mActivity, ActivityReceiveCouponListBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        ReceiveCouponListPagerAdapter meunAdapter = new ReceiveCouponListPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onSuccess(Object data) {

    }

}
