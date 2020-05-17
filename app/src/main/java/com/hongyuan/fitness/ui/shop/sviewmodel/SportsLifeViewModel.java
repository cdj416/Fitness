package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySportsLifeBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SslGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SporsLisfeBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SportsLifeMensBean;
import com.hongyuan.fitness.ui.shop.sviewpage.SportsLifeViewPagerAdapter;

public class SportsLifeViewModel extends CustomViewModel {

    private ActivitySportsLifeBinding binding;
    private SportsLifeViewPagerAdapter pageApter;
    private SslGoodsAdapter gAdapter;

    private SporsLisfeBeans.DataBean dataBean;

    public SportsLifeViewModel(CustomActivity mActivity, ActivitySportsLifeBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        pageApter = new SportsLifeViewPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(pageApter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 3);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(rihtManager);
        gAdapter = new SslGoodsAdapter<SporsLisfeBeans.DataBean.ListBean>() {
            @Override
            public String getImg(SporsLisfeBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(SporsLisfeBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(SporsLisfeBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        binding.mRec.setAdapter(gAdapter);

        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(dataBean.getList().get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });
    }

    @Override
    protected void lazyLoad() {

        mActivity.showLoading();
        //请求分类
        clearParams().setParams("category_code","sport");
        Controller.myRequest(Constants.GET_SPORT_GOODS_CATEGORY,Controller.TYPE_POST,getParams(), SportsLifeMensBean.class,this);

        //请求推荐商品
        clearParams().setParams("category_code","sport");
        Controller.myRequest(Constants.GET_SPORT_GOODS_LIST,Controller.TYPE_POST,getParams(), SporsLisfeBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof SporsLisfeBeans){
            dataBean = ((SporsLisfeBeans)data).getData();

            gAdapter.setNewData(dataBean.getList());
        }

        if(data instanceof SportsLifeMensBean){
            SportsLifeMensBean.DataBean menuBeans = ((SportsLifeMensBean)data).getData();

            binding.mViewPager.setOffscreenPageLimit(menuBeans.getList().size());
            pageApter.setData(menuBeans.getList());
        }
    }
}
