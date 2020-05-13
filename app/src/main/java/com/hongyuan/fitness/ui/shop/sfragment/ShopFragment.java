package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.MainSearchActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMenuActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.ShopViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private ImageView sortMark,mMessage,goCart;
    private LinearLayout searchBox;

    private ShopViewPagerAdapter meunAdapter;

    //分类数据
    private FirstCategoryBeans.DataBean dataBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initView(View mView) {
        searchBox = mView.findViewById(R.id.searchBox);
        layoutMenu = mView.findViewById(R.id.layoutMenu);
        mViewPager = mView.findViewById(R.id.mViewPager);
        sortMark = mView.findViewById(R.id.sortMark);
        mMessage = mView.findViewById(R.id.mMessage);
        goCart = mView.findViewById(R.id.goCart);

        sortMark.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("menu",dataBean);
            startActivity(ShopMenuActivity.class,bundle);
        });
        mMessage.setOnClickListener(v -> {
            startActivity(ShopMessageActivity.class,null);
        });

        meunAdapter = new ShopViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);

        searchBox.setOnClickListener(v -> {
            //startActivity(ShopSearchActivity.class,null);
            startActivity(MainSearchActivity.class,null);
        });
        goCart.setOnClickListener(v ->{
            startActivity(SCartActivity.class,null);
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), FirstCategoryBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof FirstCategoryBeans){
            dataBean = ((FirstCategoryBeans)data).getData();

            mViewPager.setOffscreenPageLimit(dataBean.getList().size());
            meunAdapter.setData(dataBean.getList());

        }
    }

}
