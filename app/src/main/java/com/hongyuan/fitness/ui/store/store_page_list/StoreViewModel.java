package com.hongyuan.fitness.ui.store.store_page_list;

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
import com.hongyuan.fitness.databinding.ActivityStoreBinding;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.util.LocationBean;

public class StoreViewModel extends CustomViewModel {

    private ActivityStoreBinding binding;
    private StoreAdapter adapter;
    private StoreBean bean;

    public StoreViewModel(CustomActivity mActivity, ActivityStoreBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableRefresh(true);
        setEnableLoadMore(true);

        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(menuManager);
        adapter = new StoreAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @SingleClick(2000)
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("os_id",String.valueOf(bean.getData().getList().get(position).getOs_id()));
                startActivity(StoreDetailActivity.class,bundle);
            }
        });
    }

    @Override
    public void refreshData(){
        bean = null;
        lazyLoad();
    }
    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("lat", LocationBean.getInstance().getLat())
                .setParams("lng",LocationBean.getInstance().getLng());
        Controller.myRequest(Constants.GET_OFFLINE_STORE_LIST_JULI,Controller.TYPE_POST,getParams(), StoreBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof StoreBean && isSuccess(data)){
            StoreBean pageData = (StoreBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    bean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(bean != null && bean.getData() != null &&
                    bean.getData().getList() != null &&
                    bean.getData().getList().size() > 0){
                adapter.setNewData(bean.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
