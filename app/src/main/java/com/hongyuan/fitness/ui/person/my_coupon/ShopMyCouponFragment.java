package com.hongyuan.fitness.ui.person.my_coupon;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

public class ShopMyCouponFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;

    private MyCouponPagerAdapter meunAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_store_mycoupon;
    }

    @Override
    public void initView(View mView) {
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);

        meunAdapter = new MyCouponPagerAdapter(getChildFragmentManager(),MyCouponPagerAdapter.SHOP_COUPON);
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
