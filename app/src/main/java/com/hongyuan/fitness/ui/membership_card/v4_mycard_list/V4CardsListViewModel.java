package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import android.util.Log;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityV4CardListBinding;

public class V4CardsListViewModel extends CustomViewModel {

    private ActivityV4CardListBinding binding;

    public V4CardsListViewModel(CustomActivity mActivity, ActivityV4CardListBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        V4CardsListViewPagerAdapter meunAdapter = new V4CardsListViewPagerAdapter(mActivity.getSupportFragmentManager(),
                getBundle().getString("os_id"),
                getBundle().getString("osl_id"));
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
