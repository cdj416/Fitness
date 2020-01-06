package com.hongyuan.fitness.ui.look_img;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class MyLookImgPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public MyLookImgPagerAdapter(FragmentManager fm) {
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
        if(fragments != null){
            return fragments.size();
        }
        return 0;
    }

    /*
     * 初始化数据
     * */
    public void setData(List<MyLookImgDataBeans> mList) {
        if(mList.size() <= 0){
            return;
        }

        if(beans == null){
            beans = new ArrayList<>();
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();

        for(int i = 0 ; i < mList.size() ; i++){
            beans.add(new TitleBean(mList.get(i).getShowTitle(),i));
            fragments.add(new MyLookImgFragment().setArguments(mList.get(i).getImgUrl()));
        }

        notifyDataSetChanged();
    }

}
