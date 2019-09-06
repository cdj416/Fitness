package com.hongyuan.fitness.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_about_class.AboutClassFragment;
import com.hongyuan.fitness.ui.main.main_find.FindFragment;
import com.hongyuan.fitness.ui.main.main_home.recommend.RecommendFragment;
import com.hongyuan.fitness.ui.main.main_mall.MallFragment;
import com.hongyuan.fitness.ui.main.main_person.PersonFragment;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private CustomFragment lastFragment;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        setData();
    }

    @Override
    public CustomFragment getItem(int position) {
        return fragments.get(position);
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
        lastFragment = new PersonFragment();
        fragments.clear();
        fragments.add(new RecommendFragment());
        fragments.add(new FindFragment());
        fragments.add(new AboutClassFragment());
        fragments.add(new MallFragment());
        fragments.add(lastFragment);

        notifyDataSetChanged();
    }

    public CustomFragment getLastFragment() {
        return lastFragment;
    }
}
