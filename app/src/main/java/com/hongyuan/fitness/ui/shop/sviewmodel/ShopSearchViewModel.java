package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.Gravity;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopSearchBinding;
import com.hongyuan.fitness.ui.shop.sinterface.SearchOCDrawerListener;
import com.hongyuan.fitness.ui.shop.sviewpage.SsearchShopPagerAdapter;

public class ShopSearchViewModel extends CustomViewModel implements SearchOCDrawerListener {

    private ActivityShopSearchBinding binding;

    public ShopSearchViewModel(CustomActivity mActivity, ActivityShopSearchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        SsearchShopPagerAdapter meunAdapter = new SsearchShopPagerAdapter(mActivity.getSupportFragmentManager(),this);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.mViewPager.setOffscreenPageLimit(2);

        //关闭手势滑动打开
        //binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
    * 抽屉的打开与关闭
    * */
    @Override
    public void openOrClose(boolean flag) {
        if(flag){
            binding.drawer.openDrawer(Gravity.RIGHT);
        }else{
            binding.drawer.closeDrawer(Gravity.RIGHT);
        }
    }
}
