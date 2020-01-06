package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.only_equipment.smart_historical_data.BodyFatListNewBeans;
import com.hongyuan.fitness.ui.only_equipment.smart_historical_data.SmartHistoricalUseData;

import java.util.ArrayList;
import java.util.List;

public class AddPhysicaldataPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    //传递的数据
    private List<BodyFatListNewBeans.DataBean.ListBean> showData;

    public AddPhysicaldataPagerAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("体重",0));
        beans.add(new TitleBean("身高",1));
        beans.add(new TitleBean("BMI",2));
        beans.add(new TitleBean("胸围",3));
        beans.add(new TitleBean("腰围",4));
        beans.add(new TitleBean("臀围",5));
        beans.add(new TitleBean("大腿围",6));
        beans.add(new TitleBean("小腿围",7));
        beans.add(new TitleBean("手臂围",8));
        beans.add(new TitleBean("体脂率",9));

        fragments.add(new AddPhysicaldataFragment().setArguments("weight"));
        fragments.add(new AddPhysicaldataFragment().setArguments("height"));
        fragments.add(new AddPhysicaldataFragment().setArguments("bmi"));
        fragments.add(new AddPhysicaldataFragment().setArguments("xw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("yw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("tw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("dtw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("xtw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("sbw"));
        fragments.add(new AddPhysicaldataFragment().setArguments("bfp"));

        notifyDataSetChanged();
    }

}
