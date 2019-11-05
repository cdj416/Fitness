package com.hongyuan.fitness.ui.store.store_list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_find.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class StoreListViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;
    private String os_id;

    public StoreListViewPagerAdapter(FragmentManager fm,String os_id) {
        super(fm);
        this.os_id = os_id;
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
        //beans.add(new TitleBean("会籍卡",0));
        beans.add(new TitleBean("明星私教",1));
        beans.add(new TitleBean("私教课",2));
        beans.add(new TitleBean("精品团课",3));
        beans.add(new TitleBean("出租柜",4));
        beans.add(new TitleBean("名店照片",5));

        //fragments.add(new StoreListCardFragment().setMyArguments(getBundle("0",os_id)));
        fragments.add(new StoreListFragment().setMyArguments(getBundle("1",os_id)));
        fragments.add(new StoreListFragment().setMyArguments(getBundle("2",os_id)));
        fragments.add(new StoreListFragment().setMyArguments(getBundle("3",os_id)));
        fragments.add(new TestFragment());
        fragments.add(new StoreListFragment().setMyArguments(getBundle("5",os_id)));

        notifyDataSetChanged();
    }

    public List<TitleBean> getBeans() {
        return beans;
    }

    public View getTabView(int position, Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_private_lessons, null);
        TextView textView=view.findViewById(R.id.name);
        textView.setText(beans.get(position).getTitleName());
        return view;
    }

    private Bundle getBundle(String _type,String os_id){
        Bundle bundle = new Bundle();
        bundle.putString("_type",_type);
        bundle.putString("os_id",os_id);
        return bundle;
    }
}
