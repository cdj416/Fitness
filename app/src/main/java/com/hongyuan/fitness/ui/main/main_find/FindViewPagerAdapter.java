package com.hongyuan.fitness.ui.main.main_find;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_find.featured.FeaturedFragment;

import java.util.ArrayList;
import java.util.List;

public class FindViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public FindViewPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("精选",0));
        beans.add(new TitleBean("关注",1));
        beans.add(new TitleBean("全部",2));
        fragments.add(new FeaturedFragment().setArguments("tj"));
        fragments.add(new FeaturedFragment().setArguments("gz"));
        fragments.add(new FeaturedFragment().setArguments(""));

        notifyDataSetChanged();
    }
}
