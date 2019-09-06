package com.hongyuan.fitness.ui.out_door.run;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.out_door.run.run_plan.RunFourthFragment;
import com.hongyuan.fitness.ui.out_door.run.run_plan.RunOneFragment;
import com.hongyuan.fitness.ui.out_door.run.run_plan.RunThirdFragment;
import com.hongyuan.fitness.ui.out_door.run.run_plan.RunTwoFragment;

import java.util.ArrayList;
import java.util.List;

public class RunPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    public RunPagerAdapter(FragmentManager fm) {
        super(fm);
        setData();
    }

    @Override
    public Fragment getItem(int position) {
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
        fragments.add(new RunOneFragment());
        fragments.add(new RunTwoFragment());
        fragments.add(new RunThirdFragment());
        fragments.add(new RunFourthFragment());

        notifyDataSetChanged();
    }
}
