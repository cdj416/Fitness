package com.hongyuan.fitness.ui.training_plan.plan_program;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class PlansProgramDetailsPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments = new ArrayList<>();
    private List<TitleBean> beans = new ArrayList<>();

    public PlansProgramDetailsPagerAdapter(FragmentManager fm) {
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
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData(List<PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean> itemBeans) {
        fragments.clear();
        beans.clear();

        for(PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean itemBean : itemBeans){
            beans.add(new TitleBean("第"+itemBean.getDay()+"天",0));
            fragments.add(new PlansProgramFragment().setMyArguments(getBundle(itemBean)));
        }

        notifyDataSetChanged();
    }

    /*
    * 获取Bundle对象
    * */
    private Bundle getBundle(PlansProgramDetailsBeans.DataBean.InfoBean.ItemBean itemBean){
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",itemBean);
        return bundle;
    }
}
