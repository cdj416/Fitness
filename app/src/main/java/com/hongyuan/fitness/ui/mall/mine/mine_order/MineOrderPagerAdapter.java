package com.hongyuan.fitness.ui.mall.mine.mine_order;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_find.TestFragment;
import com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list.MineOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MineOrderPagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments = new ArrayList<>();
    private List<TitleBean> beans = new ArrayList<>();

    public MineOrderPagerAdapter(FragmentManager fm) {
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
        if(beans.size() <= 0){
            beans.add(new TitleBean("全部",0));
            beans.add(new TitleBean("会籍卡",1));
            beans.add(new TitleBean("商城",2));
            beans.add(new TitleBean("私教课",3));
            beans.add(new TitleBean("出租柜",4));
            fragments.add(new MineOrderFragment().setArguments("0"));
            fragments.add(new MineOrderFragment().setArguments("o_card"));
            fragments.add(new MineOrderFragment().setArguments("o_goods"));
            fragments.add(new MineOrderFragment().setArguments("o_pric"));
            fragments.add(new TestFragment().setArguments("null"));
        }

        notifyDataSetChanged();
    }
}
