package com.hongyuan.fitness.ui.encyclopedia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail.EncyclopediaDetailActivity;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class EncyclopediaFragment extends CustomFragment {
    private RecyclerView mRecycler;
    private EncyclopediaAdapter adapter;
    private CardView cardView;
    private Banner banner;
    private TextView bannerTitle;
    private EncyclopediaBean listBean;
    private EncyclopediaBannerBean bannerBean;

    //图片的集合
    private List<String> imgUrls = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enclopedia;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View mView) {
        //开启刷新
        setEnableRefresh(true);
        //开启分页
        setEnableLoadMore(true);

        mRecycler = mView.findViewById(R.id.mRecycler);
        banner = mView.findViewById(R.id.banner);
        bannerTitle = mView.findViewById(R.id.bannerTitle);
        cardView = mView.findViewById(R.id.cardView);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new EncyclopediaAdapter();
        mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick (2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("baike_id",String.valueOf(listBean.getData().getList().get(position).getBaike_id()));
                startActivity(EncyclopediaDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //获取轮播
        getBanner();
        getList();
    }

    /*
    * 加载更多
    * */
    @Override
    public void loadMoreData() {
        getList();
    }

    //刷新数据
    @Override
    public void refreshData() {
        lazyLoad();
    }

    /*
     * 轮播图的设定
     * */
    private void setBanner(List<String> imgUrls){
        banner.setImages(imgUrls)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).start();
    }

    private void getBanner(){
        clearParams().setParams("ft_id", getFragType())
                .setParams("page","10").setParams("curpage","1");
        Controller.myRequest(Constants.GET_BAIKE_LIST_TJ,Controller.TYPE_POST,getParams(), EncyclopediaBannerBean.class,this);
    }

    /*
    * 获取百科列表
    * */
    private void getList(){
        clearParams().setParams("ft_id", getFragType());
        Controller.myRequest(Constants.GET_BAIKE_LIST,Controller.TYPE_POST,getParams(), EncyclopediaBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof EncyclopediaBean && isSuccess(data)){
            EncyclopediaBean pageData = (EncyclopediaBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    listBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(listBean != null && listBean.getData() != null &&
                    listBean.getData().getList() != null &&
                    listBean.getData().getList().size() > 0){
                adapter.setNewData(listBean.getData().getList());
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }

        }
        if(data instanceof EncyclopediaBannerBean && isSuccess(data)){
            bannerBean = (EncyclopediaBannerBean)data;
            imgUrls.clear();
            for(EncyclopediaBannerBean.DataBean.ListBean bean : bannerBean.getData().getList()){
                imgUrls.add(bean.getBaike_img());
            }
            if(imgUrls.size() > 0){
                bannerTitle.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);
                setBanner(imgUrls);
            }else{
                bannerTitle.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);
            }
        }
    }
}
