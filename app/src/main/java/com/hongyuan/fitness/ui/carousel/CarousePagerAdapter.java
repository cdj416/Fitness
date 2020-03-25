package com.hongyuan.fitness.ui.carousel;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TitleBean;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

public class CarousePagerAdapter extends FragmentPagerAdapter {

    private List<CustomFragment> fragments;
    private List<TitleBean> beans;

    //广告实体类
    private HomeBannerBean.DataBean.ListBean advertBean;

    public CarousePagerAdapter(FragmentManager fm,HomeBannerBean.DataBean.ListBean advertBean) {
        super(fm);
        this.advertBean = advertBean;
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
    public void setData(List<HomeBannerBean.DataBean.ListBean> mList) {
        if(beans == null){
            beans = new ArrayList<>();
        }
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        fragments.clear();
        beans.clear();

        for(int i = 0 ; i < mList.size() ; i++){
            beans.add(new TitleBean("",i));
            fragments.add(new CarouseFragment().setMyArguments(getBundle(mList.get(i).getImg_src(),mList.get(i).getImg_id(),i == (mList.size() - 1))));
        }

        notifyDataSetChanged();
    }

    private Bundle getBundle(String imgUrl,int imgId,boolean isLast){
        Bundle bundle = new Bundle();
        bundle.putString("imgUrl",imgUrl);
        bundle.putBoolean("last",isLast);
        if(!BaseUtil.isValue(imgUrl)){
            bundle.putInt("imgId",imgId);
        }
        if(isLast){
            bundle.putSerializable("advertImgBean",advertBean);
        }
        return bundle;
    }
}
