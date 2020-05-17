package com.hongyuan.fitness.ui.person.fix;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class FixpCourseViwPageAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public FixpCourseViwPageAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("待上课",1));
        beans.add(new TitleBean("待评价",2));
        beans.add(new TitleBean("已上完",3));
        fragments.add(new SixpCourseAllFragment());
        fragments.add(new SixReservationFragment());
        fragments.add(new SixSiginFragment());
        fragments.add(new SixAssessFragment());

        notifyDataSetChanged();
    }
}
