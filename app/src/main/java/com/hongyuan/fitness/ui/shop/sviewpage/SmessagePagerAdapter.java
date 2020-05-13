package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.SmCommentaryFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SmFanFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SmPraiseFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SmPrivateLettersFragment;
import java.util.ArrayList;
import java.util.List;

public class SmessagePagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SmessagePagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("私信",0));
        beans.add(new TitleBean("评论",1));
        beans.add(new TitleBean("赞",2));
        beans.add(new TitleBean("粉丝",3));
        fragments.add(new SmPrivateLettersFragment());
        fragments.add(new SmCommentaryFragment());
        fragments.add(new SmPraiseFragment());
        fragments.add(new SmFanFragment());

        notifyDataSetChanged();
    }
}
