package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.SccouponsFragment;
import com.hongyuan.fitness.ui.shop.sfragment.ShopMainFragment;
import com.hongyuan.fitness.ui.shop.sfragment.ShopNextFragment;

import java.util.ArrayList;
import java.util.List;

public class SccouponsPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SccouponsPagerAdapter(FragmentManager fm) {
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
    public void setData(List<BaseBean> mList) {
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
                if(i == 0){
                    beans.add(new TitleBean("推荐",i));
                    fragments.add(new SccouponsFragment().setArguments(""));
                }else{
                    beans.add(new TitleBean("关注",i));
                    fragments.add(new SccouponsFragment().setArguments(""));
                }
            }
        }

        notifyDataSetChanged();
    }
}
