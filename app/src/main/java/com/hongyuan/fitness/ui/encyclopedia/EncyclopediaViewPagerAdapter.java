package com.hongyuan.fitness.ui.encyclopedia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.MenuPrivateLessonsBean;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<EncyclopediaMenuBeans.DataBean> beans;
    private Context mContext;

    public EncyclopediaViewPagerAdapter(Context mContext,FragmentManager fm, List<EncyclopediaMenuBeans.DataBean> beans) {
        super(fm);
        this.beans = beans;
        this.mContext = mContext;
        setData(beans);
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return beans.get(position).getBaike_category_name();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*
     * 初始化数据
     * */
    public void setData(List<EncyclopediaMenuBeans.DataBean> beans) {
        if(beans == null){
            return;
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        this.beans.clear();
        this.beans = beans;
        for (int i = 0 ; i < beans.size() ; i++){
            fragments.add(new EncyclopediaFragment().setArguments(String.valueOf(beans.get(i).getBaike_categoryid())));
        }

        notifyDataSetChanged();
    }
}
