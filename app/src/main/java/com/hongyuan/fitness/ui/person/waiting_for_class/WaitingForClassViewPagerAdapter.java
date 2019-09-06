package com.hongyuan.fitness.ui.person.waiting_for_class;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course.GroupCourseCheckFragment;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckFragment;

import java.util.ArrayList;
import java.util.List;

public class WaitingForClassViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public WaitingForClassViewPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("私教课",0));
        beans.add(new TitleBean("团体课",1));
        fragments.add(new PriviteCourseCheckFragment());
        fragments.add(new GroupCourseCheckFragment());

        notifyDataSetChanged();
    }
}
