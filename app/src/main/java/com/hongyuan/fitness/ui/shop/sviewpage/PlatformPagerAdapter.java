package com.hongyuan.fitness.ui.shop.sviewpage;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.PlatformCouponsTitleBeans;
import com.hongyuan.fitness.ui.shop.sfragment.PlatformCouponsFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SccouponsFragment;

import java.util.ArrayList;
import java.util.List;

public class PlatformPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public PlatformPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getTitleName();
    }

    @Override
    public int getCount() {
        if(fragments != null)
        return fragments.size();

        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData(List<PlatformCouponsTitleBeans.DataBean> mList) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();

        if(mList != null && mList.size() > 0){
            for(int i = 0 ; i < mList.size() ; i++){
                beans.add(new TitleBean(mList.get(i).getCoupon_for_name(),i));
                fragments.add(new PlatformCouponsFragment().setMyArguments(getBundle(mList.get(i))));
            }
        }

        notifyDataSetChanged();
    }

    private Bundle getBundle(PlatformCouponsTitleBeans.DataBean item){
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        return bundle;
    }
}
