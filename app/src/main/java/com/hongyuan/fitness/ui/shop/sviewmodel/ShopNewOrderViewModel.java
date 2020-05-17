package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopNewordersBinding;
import com.hongyuan.fitness.ui.shop.sviewpage.SnewOrderViwPageAdapter;

public class ShopNewOrderViewModel extends CustomViewModel {

    private ActivityShopNewordersBinding binding;

    private int mPosition = 0;

    public ShopNewOrderViewModel(CustomActivity mActivity, ActivityShopNewordersBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        if(getBundle() != null){
            mPosition = getBundle().getInt("position");
        }

        SnewOrderViwPageAdapter meunAdapter = new SnewOrderViwPageAdapter(mActivity.getSupportFragmentManager());
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(6);

        binding.viewPager.setCurrentItem(mPosition);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
