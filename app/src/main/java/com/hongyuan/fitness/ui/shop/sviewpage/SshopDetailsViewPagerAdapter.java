package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.SshopCommentsFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SshopDetailMainFragment;
import com.hongyuan.fitness.ui.shop.sinterface.GoOtherPageListener;

import java.util.ArrayList;
import java.util.List;

public class SshopDetailsViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;
    //评论详情
    private SshopDetailMainFragment mainFragment;

    public SshopDetailsViewPagerAdapter(FragmentManager fm, GoOtherPageListener pageListener) {
        super(fm);
        setData(pageListener);
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
    public void setData(GoOtherPageListener pageListener) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();
        beans.add(new TitleBean("商品",0));
        beans.add(new TitleBean("评论",1));
        mainFragment = new SshopDetailMainFragment(pageListener);
        fragments.add(mainFragment);
        fragments.add(new SshopCommentsFragment());

        notifyDataSetChanged();
    }

    /*
    * 打开规格弹框
    * */
    public void showGuiGe(){
        mainFragment.showGG();
    }
}
