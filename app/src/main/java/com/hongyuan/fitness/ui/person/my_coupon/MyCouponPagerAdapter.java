package com.hongyuan.fitness.ui.person.my_coupon;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.ui.person.my_collection.collection_baike.CollectionBaikeBeans;

import java.util.ArrayList;
import java.util.List;

public class MyCouponPagerAdapter extends FragmentPagerAdapter {

    public static final int SHOP_COUPON = 0X1;
    public static final int STORE_COUPON = 0X2;

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;



    public MyCouponPagerAdapter(FragmentManager fm,int type) {
        super(fm);
        if(type == STORE_COUPON){
            setData();
        }
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
        beans.add(new TitleBean("未使用",0));
        beans.add(new TitleBean("已使用",1));
        beans.add(new TitleBean("已过期",2));

        fragments.add(new MyCouponFragment().setArguments("0").setMyClass(VtwoPrivateLessonsBeans.class));
        fragments.add(new MyCouponFragment().setArguments("1").setMyClass(VtwoStarCoachBean.class));
        fragments.add(new MyCouponFragment().setArguments("2").setMyClass(CollectionBaikeBeans.class));

        notifyDataSetChanged();
    }
}
