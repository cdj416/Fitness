package com.hongyuan.fitness.ui.find.more_topic;

import android.view.View;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMoreTopicBinding;
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;

public class MoreTopicViewModel extends CustomViewModel {
    private ActivityMoreTopicBinding binding;
    private MoreTopicViewPagerAdapter meunAdapter;
    //菜单数据
    private SlectTopicLeftBeans.DataBean menuBean;

    public MoreTopicViewModel(CustomActivity mActivity, ActivityMoreTopicBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        meunAdapter = new MoreTopicViewPagerAdapter(mActivity,mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
    }

    @Override
    protected void lazyLoad() {
        //加载话题一级分类
        clearParams().setParams("parent_id","0");
        Controller.myRequest(Constants.GET_CIRCLE_CATEGORY_LIST,Controller.TYPE_POST,getParams(), SlectTopicLeftBeans.class,this);

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof SlectTopicLeftBeans){
            menuBean = ((SlectTopicLeftBeans) data).getData();
            meunAdapter.setData(menuBean.getList());
            binding.mViewPager.setOffscreenPageLimit(menuBean.getList().size());

            binding.isEmpty.setVisibility(View.GONE);
        }
    }
}
