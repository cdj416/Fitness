package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySportsLifeBinding;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SslGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SporsLisfeBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SportsLifeMensBean;
import com.hongyuan.fitness.ui.shop.sviewpage.SportsLifeViewPagerAdapter;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class SportsLifeViewModel extends CustomViewModel {

    private ActivitySportsLifeBinding binding;
    private SportsLifeViewPagerAdapter pageApter;
    private SslGoodsAdapter gAdapter;

    private SporsLisfeBeans.DataBean dataBean;
    private List<HomeBannerBean.DataBean.ListBean> banns;

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

    /*
     * 设置顶部banner
     * */
    private void setTopBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }
        binding.banner.setImages(bList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

            JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));

            JumpUtils.goAtherPage(mActivity,jumpBeans);
        }).start();
    }

    @Override
    protected void lazyLoad() {

        mActivity.showLoading();
        //请求banner
        clearParams().setParams("img_code","sport_banner").setParams("img_num","6");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);

        //请求分类
        clearParams().setParams("category_code","sport");
        Controller.myRequest(Constants.GET_SPORT_GOODS_CATEGORY,Controller.TYPE_POST,getParams(), SportsLifeMensBean.class,this);

        //请求推荐商品
        clearParams().setParams("category_code","sport");
        Controller.myRequest(Constants.GET_SPORT_GOODS_LIST,Controller.TYPE_POST,getParams(), SporsLisfeBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof HomeBannerBean){
            banns = ((HomeBannerBean)data).getData().getList();
            if(banns != null && banns.size() > 0){
                binding.bannerCard.setVisibility(View.VISIBLE);
                setTopBanner(banns);
            }
        }

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
