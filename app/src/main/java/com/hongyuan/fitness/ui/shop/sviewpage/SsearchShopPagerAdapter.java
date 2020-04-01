package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.SsearchGoodsFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SsearchShopFragment;
import com.hongyuan.fitness.ui.shop.sinterface.SearchOCDrawerListener;

import java.util.ArrayList;
import java.util.List;

public class SsearchShopPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SsearchShopPagerAdapter(FragmentManager fm,SearchOCDrawerListener drawerListener) {
        super(fm);
        setData(drawerListener);
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
    public void setData(SearchOCDrawerListener drawerListener) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("全部",0));
        beans.add(new TitleBean("店铺",1));
        fragments.add(new SsearchGoodsFragment(drawerListener));
        fragments.add(new SsearchShopFragment());

        notifyDataSetChanged();
    }
}
