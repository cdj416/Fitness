package com.hongyuan.fitness.ui.main.main_home.near_store;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.ui.store.StoreDetailActivity;
import com.hongyuan.fitness.util.LocationBean;


public class NearbyStoreFragment extends CustomFragment {

    private RecyclerView mRecycler;
    private SelectStoreAdapter adapter;
    private StoreBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nearbystore;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView(View mView) {
        //开启刷新功能
        setEnableRefresh(true);
        setEnableLoadMore(true);
        mRecycler = mView.findViewById(R.id.mRecycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        adapter = new SelectStoreAdapter();
        mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(mRecycler));

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("os_id",String.valueOf(bean.getData().getList().get(position).getOs_id()));
            startActivity(StoreDetailActivity.class,bundle);
        });
    }

    /*
     * 加载更多
     * */
    @Override
    public void loadMoreData() {
        lazyLoad();
    }

    //刷新数据
    @Override
    public void refreshData() {
        bean = null;
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
                setPromtView(SHOW_DATA);
            }else{
                setPromtView(SHOW_EMPTY);
            }
        }
    }
}
