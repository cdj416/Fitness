package com.hongyuan.fitness.ui.person.my_collection;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.person.my_collection.collection_baike.CollectionBaikeBeans;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public MyCollectionPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("课程",0));
        beans.add(new TitleBean("私教",1));
        beans.add(new TitleBean("百科",2));

        fragments.add(new MyCollectionFragment().setArguments("cp").setMyClass(VtwoPrivateLessonsBeans.class));
        fragments.add(new MyCollectionFragment().setArguments("coach").setMyClass(VtwoStarCoachBean.class));
        fragments.add(new MyCollectionFragment().setArguments("baike").setMyClass(CollectionBaikeBeans.class));

        notifyDataSetChanged();
    }
}
