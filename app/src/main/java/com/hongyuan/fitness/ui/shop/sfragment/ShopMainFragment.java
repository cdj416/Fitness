package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.CustomRecyclerView;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.shop.sactivity.CollectCouponsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.IntegralGoodsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.PromoteGoodsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SportsLifeActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.ShopMainGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.HabitGoddsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopMainBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class ShopMainFragment extends CustomFragment implements View.OnClickListener {

    private Banner topBanner,sBanner;
    private RecyclerView mRec;
    private TextView goIntegral,goCoupons,goSportsLife,goHot;
    private CustomRecyclerView daysRes,newsRec,hotRec,storeRec;

    private SMGoodsAdapter gAdapter;
    private ShopMainGoodsAdapter daysAdapter,newsAdapter,hotsAdapter,stroeAdapter;

    private ShopMainBeans.DataBean dataBean;

    //猜你喜欢
    private List<HabitGoddsBeans.DataBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_main;
    }

    @Override
    public void initView(View mView) {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        topBanner = mView.findViewById(R.id.topBanner);
        sBanner = mView.findViewById(R.id.sBanner);
        mRec = mView.findViewById(R.id.mRec);
        newsRec = mView.findViewById(R.id.newsRec);
        hotRec = mView.findViewById(R.id.hotRec);
        storeRec = mView.findViewById(R.id.storeRec);

        goIntegral = mView.findViewById(R.id.goIntegral);
        goCoupons = mView.findViewById(R.id.goCoupons);
        daysRes = mView.findViewById(R.id.daysRes);
        goSportsLife = mView.findViewById(R.id.goSportsLife);
        goHot = mView.findViewById(R.id.goHot);

        GridLayoutManager layoutManager =
                new GridLayoutManager(mActivity,2);
        mRec.setLayoutManager(layoutManager);
        gAdapter = new SMGoodsAdapter<HabitGoddsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(HabitGoddsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        mRec.setAdapter(gAdapter);
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        //每日优选
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        daysRes.setLayoutManager(manager1);
        daysAdapter = new ShopMainGoodsAdapter<ShopMainBeans.DataBean.GoodsSelectedBean>() {
            @Override
            public String getImg(ShopMainBeans.DataBean.GoodsSelectedBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(ShopMainBeans.DataBean.GoodsSelectedBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(ShopMainBeans.DataBean.GoodsSelectedBean item) {
                return BaseUtil.getNoZoon(item.getG_price());
            }

            @Override
            public boolean isStore() {
                return false;
            }
        };
        daysRes.setAdapter(daysAdapter);
        daysAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(dataBean.getGoods_selected().get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        //新品推荐
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        newsRec.setLayoutManager(manager2);
        newsAdapter = new ShopMainGoodsAdapter<ShopMainBeans.DataBean.GoodsNewBean>() {
            @Override
            public String getImg(ShopMainBeans.DataBean.GoodsNewBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(ShopMainBeans.DataBean.GoodsNewBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(ShopMainBeans.DataBean.GoodsNewBean item) {
                return BaseUtil.getNoZoon(item.getG_price());
            }

            @Override
            public boolean isStore() {
                return false;
            }
        };
        newsRec.setAdapter(newsAdapter);
        newsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(dataBean.getGoods_new().get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        //新品推荐
        LinearLayoutManager manager3 = new LinearLayoutManager(getContext());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        hotRec.setLayoutManager(manager3);
        hotsAdapter = new ShopMainGoodsAdapter<ShopMainBeans.DataBean.GoodsHotBean>() {
            @Override
            public String getImg(ShopMainBeans.DataBean.GoodsHotBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(ShopMainBeans.DataBean.GoodsHotBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(ShopMainBeans.DataBean.GoodsHotBean item) {
                return BaseUtil.getNoZoon(item.getG_price());
            }

            @Override
            public boolean isStore() {
                return false;
            }
        };
        hotRec.setAdapter(hotsAdapter);
        hotsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(dataBean.getGoods_hot().get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });


        //精选商家
        LinearLayoutManager manager4 = new LinearLayoutManager(getContext());
        manager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        storeRec.setLayoutManager(manager4);
        stroeAdapter = new ShopMainGoodsAdapter<ShopMainBeans.DataBean.StoreSelectedBean>() {
            @Override
            public String getImg(ShopMainBeans.DataBean.StoreSelectedBean item) {
                return item.getStore_logo();
            }

            @Override
            public String getName(ShopMainBeans.DataBean.StoreSelectedBean item) {
                return item.getStore_name();
            }

            @Override
            public String getPrice(ShopMainBeans.DataBean.StoreSelectedBean item) {
                return "";
            }

            @Override
            public boolean isStore() {
                return true;
            }
        };
        storeRec.setAdapter(stroeAdapter);
        stroeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("store_id",String.valueOf(dataBean.getStore_selected().get(position).getStore_id()));
            startActivity(SstoreActivity.class,bundle);
        });



        goIntegral.setOnClickListener(this);
        goCoupons.setOnClickListener(this);
        goSportsLife.setOnClickListener(this);
        goHot.setOnClickListener(this);
    }

    /*
    * 设置顶部banner
    * */
    private void setTopBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }
        topBanner.setImages(bList)
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
    /*
    * 设置顶部banner
    * */
    private void setSBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }
        sBanner.setImages(bList)
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
        clearParams();
        Controller.myRequest(Constants.GET_BUSINESS_INDEX,Controller.TYPE_POST,getParams(), ShopMainBeans.class,this);

        //获取第一个幻灯片
        clearParams().setParams("img_code","business_hd_first").setParams("img_num","6");
        Controller.myRequest(ConstantsCode.FIRST_BANNER,Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
        //获取第二个幻灯片
        clearParams().setParams("img_code","business_hd_second").setParams("img_num","6");
        Controller.myRequest(ConstantsCode.SECOND_BANNER,Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);


        getLike();
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        getLike();
    }


    /*
    * 获取猜你喜欢的商品
    * */
    private void getLike(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_HABIT_GODDS_LIST,Controller.TYPE_POST,getParams(), HabitGoddsBeans.class,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goIntegral:
                startActivity(IntegralGoodsActivity.class,null);
                break;
            case R.id.goCoupons:
                startActivity(CollectCouponsActivity.class,null);
                break;
            case R.id.goSportsLife:
                startActivity(SportsLifeActivity.class,null);
                break;
            case R.id.goHot:
                startActivity(PromoteGoodsActivity.class,null);
                break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof ShopMainBeans){
            dataBean = ((ShopMainBeans)data).getData();

            daysAdapter.setNewData(dataBean.getGoods_selected());
            newsAdapter.setNewData(dataBean.getGoods_new());
            hotsAdapter.setNewData(dataBean.getGoods_hot());
            stroeAdapter.setNewData(dataBean.getStore_selected());
        }

        if(data instanceof HabitGoddsBeans){
            List<HabitGoddsBeans.DataBean.ListBean> list = ((HabitGoddsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                gAdapter.setNewData(mList);
                //setPromtView(SHOW_DATA);
            }else{
                //setPromtView(SHOW_EMPTY);
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.FIRST_BANNER){
            List<HomeBannerBean.DataBean.ListBean> banners1 = ((HomeBannerBean)data).getData().getList();
            setTopBanner(banners1);
        }
        if(code == ConstantsCode.SECOND_BANNER){
            List<HomeBannerBean.DataBean.ListBean> banners2 = ((HomeBannerBean)data).getData().getList();
            setSBanner(banners2);
        }
    }
}
