package com.hongyuan.fitness.ui.shop.sviewpage;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import java.util.List;

public class ShopViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public ShopViewPagerAdapter(FragmentManager fm,List<CustomFragment> fragments,List<TitleBean> beans) {
        super(fm);
        this.fragments = fragments;
        this.beans = beans;
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
        if(fragments != null)
        return fragments.size();

        return 0;
    }
}
