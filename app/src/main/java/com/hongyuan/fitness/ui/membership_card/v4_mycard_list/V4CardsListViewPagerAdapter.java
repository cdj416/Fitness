package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;


import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class V4CardsListViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;
    private String os_id;
    private String osl_id;

    public V4CardsListViewPagerAdapter(FragmentManager fm,String os_id,String osl_id) {
        super(fm);
        this.os_id = os_id;
        this.osl_id = osl_id;
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
        beans.add(new TitleBean("门店卡",0));
        //beans.add(new TitleBean("通用卡",1));
        fragments.add(new V4CardsListFragment().setMyArguments(getBundle("1",os_id,osl_id)));
        //fragments.add(new V4CardsListFragment().setMyArguments(getBundle("2",os_id,osl_id)));

        notifyDataSetChanged();
    }

    private Bundle getBundle(String _type,String os_id,String osl_id){
        Bundle bundle = new Bundle();
        bundle.putString("_type",_type);
        bundle.putString("os_id",os_id);
        bundle.putString("osl_id",osl_id);
        return bundle;
    }
}
