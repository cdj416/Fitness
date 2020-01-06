package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.prompt;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class WristbandPromptPageAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public WristbandPromptPageAdapter(FragmentManager fm) {
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
        if(fragments != null){
            return fragments.size();
        }
        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData() {
        if(beans == null){
            beans = new ArrayList<>();
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("●",0));
        beans.add(new TitleBean("●",1));

        fragments.add(new ShowImgFragment().setArguments("0"));
        fragments.add(new ShowImgFragment().setArguments("1"));

        notifyDataSetChanged();
    }
}
