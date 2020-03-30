package com.hongyuan.fitness.ui.main.main_mall;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.ui.mall.GoodActivity;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsActivity;
import com.hongyuan.fitness.ui.mall.mine.mine_order.MineOrderActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.JumpUtils;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class MallFragment extends CustomFragment {

    private Banner banner;
    private RecyclerView mRecycler,menuRecycler;
    private MallAdapter adapter;
    private MallMenuAdapter mallMenuAdapter;
    private RelativeLayout goSecond;
    private CardView cardViewBox;

    private MallBeans mallBeans;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initView(View mView) {
        //开启下拉刷新
        setEnableRefresh(true);
        //添加标题
        getmTitle().setCentreText("商城");
        getmTitle().setRightText("我的");
        getmTitle().getRightView().setOnClickListener(v -> {
            startActivity(MineOrderActivity.class,null);
        });

        banner = mView.findViewById(R.id.banner);
        mRecycler = mView.findViewById(R.id.mRecycler);
        menuRecycler = mView.findViewById(R.id.menuRecycler);
        goSecond = mView.findViewById(R.id.goSecond);
        cardViewBox = mView.findViewById(R.id.cardViewBox);

        LinearLayoutManager menuManager = new LinearLayoutManager(getContext());
        menuManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menuRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,90,0x00000000));
        menuRecycler.setLayoutManager(menuManager);
        mallMenuAdapter = new MallMenuAdapter();
        menuRecycler.setAdapter(mallMenuAdapter);
        mallMenuAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            startActivity(GoodActivity.class,bundle);
        });


        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecycler.setLayoutManager(layoutManager);
        /*mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.HORIZONTAL_LIST,24,0x00000000));*/
        mRecycler.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST,24,0x00000000));
        mRecycler.setLayoutManager(layoutManager);
        adapter = new MallAdapter();
        adapter.addFooterView(getFooterHeight(mRecycler));
        mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(mallBeans.getData().getList().get(position).getG_id()));
            startActivity(GoodDetailsActivity.class,bundle);
        });

        goSecond.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position",0);
            startActivity(GoodActivity.class,bundle);
        });
    }

    /*
     * 轮播图的设定
     * */
    private void setBanner(List<HomeBannerBean.DataBean.ListBean> bannerList){
        List<String> bList = new ArrayList<>();
        for(int i = 0 ; i < bannerList.size() ; i++){
            bList.add(bannerList.get(i).getImg_src());
        }
        banner.setImages(bList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {
            JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));
            jumpBeans.setImg_href(bannerList.get(position).getImg_href());
            JumpUtils.goAtherPage(mActivity,jumpBeans);
        }).start();
    }

    @Override
    protected void lazyOnceLoad() {
        //加载商品一级列表
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), MallMenuBeans.class,this);
        //读取banner
        clearParams().setParams("img_code","productlist_hd").setParams("img_num","8");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("first_category_id","0").setParams("second_category_id","0");
        Controller.myRequest(Constants.GET_GOODS_LIST,Controller.TYPE_POST,getParams(), MallBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof HomeBannerBean){
            HomeBannerBean homeBannerBean = (HomeBannerBean)data;
            if(homeBannerBean.getData().getList() != null && homeBannerBean.getData().getList().size() > 0){
                setBanner(homeBannerBean.getData().getList());
                cardViewBox.setVisibility(View.VISIBLE);
            }else{
                cardViewBox.setVisibility(View.GONE);
            }

        }

        if(data instanceof MallBeans){
            mallBeans = (MallBeans)data;
            adapter.setNewData(mallBeans.getData().getList());
        }
        if(data instanceof MallMenuBeans){
            MallMenuBeans mallMenuBeans = (MallMenuBeans)data;
            mallMenuAdapter.setNewData(mallMenuBeans.getData().getList());
        }
    }

}
