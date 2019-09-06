package com.hongyuan.fitness.ui.main.main_home;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_home.near_store.NearbyStoreFragment;
import com.hongyuan.fitness.ui.main.main_home.recommend.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public HomeViewPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("推荐",0));
        beans.add(new TitleBean("附近门店",1));
        fragments.add(new RecommendFragment());
        fragments.add(new NearbyStoreFragment());

        notifyDataSetChanged();
    }
}
