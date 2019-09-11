package com.hongyuan.fitness.ui.encyclopedia;

import android.view.View;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaBinding;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaViewModel extends CustomViewModel {
    private ActivityEncyclopediaBinding binding;
    private EncyclopediaViewPagerAdapter meunAdapter;
    //菜单数据
    private EncyclopediaMenuBeans menuBean;

    public EncyclopediaViewModel(CustomActivity mActivity, ActivityEncyclopediaBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        List<EncyclopediaMenuBeans.DataBean> mList = new ArrayList<>();
        mList.add(new EncyclopediaMenuBeans.DataBean(0,"全部",true));
        meunAdapter = new EncyclopediaViewPagerAdapter(mActivity,mActivity.getSupportFragmentManager(),mList);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
    }

    @Override
    protected void lazyLoad() {
        //加载私教课类型
        Controller.myRequest(Constants.V3_GET_BAIKE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), EncyclopediaMenuBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof EncyclopediaMenuBeans){
            menuBean = (EncyclopediaMenuBeans)data;
            menuBean.getData().add(0,new EncyclopediaMenuBeans.DataBean(0,"推荐",true));
            meunAdapter.setData(menuBean.getData());
            binding.mViewPager.setOffscreenPageLimit(menuBean.getData().size());

            binding.isEmpty.setVisibility(View.GONE);
        }
    }
}
