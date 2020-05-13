package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.Gravity;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityShopSearchBinding;
import com.hongyuan.fitness.ui.shop.sactivity.MainSearchActivity;
import com.hongyuan.fitness.ui.shop.sinterface.SearchOCDrawerListener;
import com.hongyuan.fitness.ui.shop.sviewpage.SsearchShopPagerAdapter;
import com.hongyuan.fitness.util.BaseUtil;

public class ShopSearchViewModel extends CustomViewModel implements SearchOCDrawerListener {

    private ActivityShopSearchBinding binding;

    public ShopSearchViewModel(CustomActivity mActivity, ActivityShopSearchBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {

        binding.searchText.setText((getBundle() != null && BaseUtil.isValue(getBundle().getString("showText"))) ? getBundle().getString("showText") : "");

        SsearchShopPagerAdapter meunAdapter = new SsearchShopPagerAdapter(mActivity.getSupportFragmentManager(),this);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.mViewPager.setOffscreenPageLimit(2);

        binding.searchText.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("showText", BaseUtil.isValue(binding.searchText.getText().toString()) ? binding.searchText.getText().toString() : "");
            startActivity(MainSearchActivity.class,bundle);
        });

        //关闭手势滑动打开
        //binding.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        binding.cancelText.setOnClickListener(v -> mActivity.finish());
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
