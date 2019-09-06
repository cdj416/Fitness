package com.hongyuan.fitness.ui.mall;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_mall.MallMenuBeans;
import com.hongyuan.fitness.ui.mall.good_list.GoodListFragment;

import java.util.ArrayList;
import java.util.List;

public class GoodViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments = new ArrayList<>();
    private List<MallMenuBeans.DataBean.ListBean> beans = new ArrayList<>();

    public GoodViewPagerAdapter(FragmentManager fm) {
        super(fm);
        //setData();
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getCategory_name();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData(List<MallMenuBeans.DataBean.ListBean> beans) {
        this.beans = beans;
        for(MallMenuBeans.DataBean.ListBean bean:beans){
            fragments.add(new GoodListFragment().setArguments(String.valueOf(bean.getCategory_id())));
        }
        notifyDataSetChanged();
    }
}
