package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sbeans.SgCollectGoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SsCollectBeans;
import com.hongyuan.fitness.ui.shop.sfragment.SgCollectFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SsCollectFragment;

import java.util.ArrayList;
import java.util.List;

public class ScollectPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public ScollectPagerAdapter(FragmentManager fm) {
        super(fm);
        setData();
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
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("商品",0));
        beans.add(new TitleBean("店铺",1));
        fragments.add(new SgCollectFragment().setArguments("product").setMyClass(SgCollectGoodsBeans.class));
        fragments.add(new SsCollectFragment().setArguments("store").setMyClass(SsCollectBeans.class));

        notifyDataSetChanged();
    }
}
