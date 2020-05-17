package com.hongyuan.fitness.ui.shop.sviewpage;


import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_find.TestFragment;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SportsLifeMensBean;
import com.hongyuan.fitness.ui.shop.sfragment.ShopMainFragment;
import com.hongyuan.fitness.ui.shop.sfragment.ShopNextFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SportsLifeFragment;

import java.util.ArrayList;
import java.util.List;

public class SportsLifeViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SportsLifeViewPagerAdapter(FragmentManager fm) {
        super(fm);
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

    /*
     * 初始化数据
     * */
    public void setData(List<SportsLifeMensBean.DataBean.ListBean> mList) {
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if(beans == null){
            beans = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();

        if(mList != null && mList.size() > 0){
            for(int i = 0 ; i < mList.size() ; i++){
                beans.add(new TitleBean(mList.get(i).getCategory_name(),i));
                fragments.add(new SportsLifeFragment().setMyArguments(getBundle(mList.get(i))));
            }
        }

        notifyDataSetChanged();
    }

    private Bundle getBundle(SportsLifeMensBean.DataBean.ListBean item){
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        return bundle;
    }
}
