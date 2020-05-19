package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityGoodsPromoteBinding;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sadapter.SMGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SsearchShopAdapter;
import com.hongyuan.fitness.ui.shop.sadapter.SslGoodsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.HotsGoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShopsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ShouYIGoodsBeans;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class PromoteGoodsViewModel extends CustomViewModel {

    private AcitivityGoodsPromoteBinding binding;

    private SsearchShopAdapter storeAdapter;
    private List<ShopsBeans.DataBean.ListBean> storeList;

    private SslGoodsAdapter gAdapter;
    private List<HotsGoodsBeans.DataBean.ListBean> gList;

    private SMGoodsAdapter bAdapter;
    private List<ShouYIGoodsBeans.DataBean.ListBean> bList;
    private List<HomeBannerBean.DataBean.ListBean> banns;

    public PromoteGoodsViewModel(CustomActivity mActivity, AcitivityGoodsPromoteBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        //每日优选
        LinearLayoutManager manager1 = new LinearLayoutManager(mActivity);
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.storeRec.setLayoutManager(manager1);
        storeAdapter = new SsearchShopAdapter();
        binding.storeRec.setAdapter(storeAdapter);
        storeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.goStore){
                Bundle bundle = new Bundle();
                bundle.putString("store_id",String.valueOf(storeList.get(position).getStore_id()));
                startActivity(SstoreActivity.class,bundle);
            }
            if(view.getId() == R.id.img1){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(storeList.get(position).getGoods_list().get(0).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img2){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(storeList.get(position).getGoods_list().get(1).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
            if(view.getId() == R.id.img3){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(storeList.get(position).getGoods_list().get(2).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
        });


        GridLayoutManager rihtManager = new GridLayoutManager(mActivity, 3);
        rihtManager.setOrientation(RecyclerView.VERTICAL);
        binding.tgRec.setLayoutManager(rihtManager);
        gAdapter = new SslGoodsAdapter<HotsGoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(HotsGoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(HotsGoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(HotsGoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        binding.tgRec.setAdapter(gAdapter);

        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(gList.get(position).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        });

        GridLayoutManager bGoodM = new GridLayoutManager(mActivity, 2);
        bGoodM.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(bGoodM);
        bAdapter = new SMGoodsAdapter<ShouYIGoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(ShouYIGoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(ShouYIGoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(ShouYIGoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }
        };
        binding.mRec.setAdapter(bAdapter);

        bAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(bList.get(position).getG_id()));
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
        clearParams().setParams("img_code","tg_banner").setParams("img_num","6");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
        //推广商品商家
        clearParams();
        Controller.myRequest(Constants.GET_FX_STORE_LIST,Controller.TYPE_POST,getParams(), ShopsBeans.class,this);

        //推广商品商家
        clearParams();
        Controller.myRequest(Constants.GET_FX_GOODS_LIST,Controller.TYPE_POST,getParams(), ShouYIGoodsBeans.class,this);

        //热门推广商品
        clearParams();
        Controller.myRequest(Constants.GET_HOT_FX_GOODS_LIST,Controller.TYPE_POST,getParams(), HotsGoodsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof HomeBannerBean){
            banns = ((HomeBannerBean)data).getData().getList();
            if(banns != null && banns.size() > 0){
                binding.bannerCard.setVisibility(View.VISIBLE);
                setTopBanner(banns);
            }
        }
        if(data instanceof ShopsBeans){
            storeList = ((ShopsBeans)data).getData().getList();

            storeAdapter.setNewData(storeList);
        }

        if(data instanceof HotsGoodsBeans){
            gList = ((HotsGoodsBeans)data).getData().getList();
            gAdapter.setNewData(gList);

        }

        if(data instanceof ShouYIGoodsBeans){
            bList = ((ShouYIGoodsBeans)data).getData().getList();

            bAdapter.setNewData(bList);
        }

    }
}
