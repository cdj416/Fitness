package com.hongyuan.fitness.ui.shop.sviewpage;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.FindCrileFragment;
import com.hongyuan.fitness.ui.shop.sfragment.FindPostFragment;
import com.hongyuan.fitness.ui.shop.sfragment.FindUserFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchFindViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SearchFindViewPagerAdapter(FragmentManager fm) {
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
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("用户",0));
        beans.add(new TitleBean("帖子",1));
        beans.add(new TitleBean("话题",2));
        fragments.add(new FindUserFragment());
        fragments.add(new FindPostFragment());
        fragments.add(new FindCrileFragment());

        notifyDataSetChanged();
    }
}
