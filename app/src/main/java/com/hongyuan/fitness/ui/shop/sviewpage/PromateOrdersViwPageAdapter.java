package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.PromateOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class PromateOrdersViwPageAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public PromateOrdersViwPageAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("全部",0));
        beans.add(new TitleBean("已付款",1));
        beans.add(new TitleBean("已完成",2));
        beans.add(new TitleBean("已失效",3));
        fragments.add(new PromateOrderFragment().setArguments(""));
        fragments.add(new PromateOrderFragment().setArguments("3"));
        fragments.add(new PromateOrderFragment().setArguments("8"));
        fragments.add(new PromateOrderFragment().setArguments("3"));

        notifyDataSetChanged();
    }
}
