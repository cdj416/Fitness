package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityShopLikesBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.GoodsLikesAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.LikeGoodsBeans;

import java.util.List;

public class ShopLikesViewModel extends CustomViewModel {

    private ActivityShopLikesBinding binding;

    private GoodsLikesAdapter adapter;
    private List<LikeGoodsBeans.DataBean.ListBean> mList;

    public ShopLikesViewModel(CustomActivity mActivity, ActivityShopLikesBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }


    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new GoodsLikesAdapter<LikeGoodsBeans.DataBean.ListBean>() {
            @Override
            public String getImg(LikeGoodsBeans.DataBean.ListBean item) {
                return item.getG_img();
            }

            @Override
            public String getName(LikeGoodsBeans.DataBean.ListBean item) {
                return item.getG_name();
            }

            @Override
            public String getPrice(LikeGoodsBeans.DataBean.ListBean item) {
                return item.getG_price();
            }

            @Override
            public String getStore(LikeGoodsBeans.DataBean.ListBean item) {
                return "店铺："+item.getStore_name();
            }
        };
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(mList.get(position).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }
        });
    }

    @Override
    public void refreshData() {
        curPage = FIRST_PAGE;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("g_id",getBundle().getString("g_id"));
        Controller.myRequest(Constants.GET_SIMLAR_GOODS_LIST,Controller.TYPE_POST,getParams(), LikeGoodsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof LikeGoodsBeans){
            List<LikeGoodsBeans.DataBean.ListBean> list = ((LikeGoodsBeans)data).getData().getList();
            if(curPage == FIRST_PAGE){
                mList = list;
            }else{
                if(list != null && list.size() > 0){
                    mList.addAll(list);
                }else{
                    mActivity.refresh.finishLoadMoreWithNoMoreData();
                }
            }

            if(mList != null && mList.size() > 0){
                adapter.setNewData(mList);
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
