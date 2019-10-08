package com.hongyuan.fitness.ui.find.more_topic;


import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaFragment;
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;

import java.util.ArrayList;
import java.util.List;

public class MoreTopicViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<SlectTopicLeftBeans.DataBean.ListBean> beans;
    private Context mContext;

    public MoreTopicViewPagerAdapter(Context mContext, FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
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
        if(fragments != null){
            return fragments.size();
        }
        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData(List<SlectTopicLeftBeans.DataBean.ListBean> beans) {
        if(beans == null){
            return;
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        this.beans = beans;
        for (int i = 0 ; i < beans.size() ; i++){
            fragments.add(new MoreTopFragment().setArguments(String.valueOf(beans.get(i).getCategory_id())));
        }

        notifyDataSetChanged();
    }
}
