package com.hongyuan.fitness.ui.shop.sviewpage;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.shop.sfragment.AfterSalesFragment;
import com.hongyuan.fitness.ui.shop.sfragment.SnewOrdersFragment;
import java.util.ArrayList;
import java.util.List;

public class SnewOrderViwPageAdapter extends FragmentPagerAdapter {

    //订单状态 1 待付款 2已取消 3已付款 4已发货5已提货 8已确定 9最终完成.is_evaluation 1已评价 0未评价state=8 加is_evaluation

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    public SnewOrderViwPageAdapter(FragmentManager fm) {
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
        beans.add(new TitleBean("全部",0));
        beans.add(new TitleBean("待付款",1));
        beans.add(new TitleBean("待发货",2));
        beans.add(new TitleBean("待收货",3));
        beans.add(new TitleBean("待评价",4));
        beans.add(new TitleBean("退换/售后",5));
        fragments.add(new SnewOrdersFragment().setArguments(""));
        fragments.add(new SnewOrdersFragment().setArguments("1"));
        fragments.add(new SnewOrdersFragment().setArguments("3"));
        fragments.add(new SnewOrdersFragment().setArguments("4"));
        fragments.add(new SnewOrdersFragment().setArguments("is_evaluation"));
        fragments.add(new AfterSalesFragment());

        notifyDataSetChanged();
    }
}
