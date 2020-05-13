package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.SstoreGoodsFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SstoreMainFragment;
import java.util.ArrayList;
import java.util.List;

public class SstoreViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;
    private SstoreMainFragment mainFragment;

    public SstoreViewPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("首页",0));
        beans.add(new TitleBean("全部商品",1));

        mainFragment = new SstoreMainFragment();
        fragments.add(mainFragment);
        fragments.add(new SstoreGoodsFragment());

        notifyDataSetChanged();
    }

    public void setImgs(String imgs){
        mainFragment.setImgs(imgs);
    }
}
