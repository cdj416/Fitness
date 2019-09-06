package com.hongyuan.fitness.ui.mall;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGoodBinding;
import com.hongyuan.fitness.ui.main.main_mall.MallMenuBeans;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;

import org.greenrobot.eventbus.EventBus;

public class GoodViewModel extends CustomViewModel {

    private ActivityGoodBinding binding;
    private GoodViewPagerAdapter viewPagerAdapter;

    public GoodViewModel(CustomActivity mActivity, ActivityGoodBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        viewPagerAdapter = new GoodViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(viewPagerAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.meText.setOnClickListener(v -> startActivity(MineOrderActivity.class,null));
        binding.backImg.setOnClickListener(v -> mActivity.finish());

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().post(ConstantsCode.EB_SEARCH_GOODS,s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void lazyLoad() {
        //加载商品一级列表
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), MallMenuBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MallMenuBeans){
            MallMenuBeans mallMenuBeans = (MallMenuBeans)data;
            viewPagerAdapter.setData(mallMenuBeans.getData().getList());
            binding.mViewPager.setOffscreenPageLimit(mallMenuBeans.getData().getList().size());
            binding.mViewPager.setCurrentItem(getBundle().getInt("position",0));
        }
    }
}
