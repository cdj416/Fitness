package com.hongyuan.fitness.ui.shop.sfragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMenuActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopSearchActivity;
import com.hongyuan.fitness.ui.shop.sviewpage.ShopViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends CustomFragment {

    private TabLayout layoutMenu;
    private ViewPager mViewPager;
    private ImageView sortMark;
    private LinearLayout searchBox;

    private ShopViewPagerAdapter meunAdapter;

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

        sortMark.setOnClickListener(v -> {
            startActivity(ShopMenuActivity.class,null);
        });

        meunAdapter = new ShopViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(meunAdapter);
        layoutMenu.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(getList().size());
        meunAdapter.setData(getList());

        searchBox.setOnClickListener(v -> {
            startActivity(ShopSearchActivity.class,null);
        });
    }

    @Override
    public void onSuccess(Object data) {

    }


    /*
    * 获取假数据
    * */
    private List<BaseBean> getList(){
        List<BaseBean> mList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            BaseBean baseBean = new BaseBean();
            mList.add(baseBean);
        }
        return mList;
    }

}
