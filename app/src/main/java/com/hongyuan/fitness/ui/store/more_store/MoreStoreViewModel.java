package com.hongyuan.fitness.ui.store.more_store;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.databinding.ActivityMoreStoreBinding;
import com.hongyuan.fitness.ui.main.main_home.near_store.SelectStoreAdapter;
import com.hongyuan.fitness.util.LocationBean;

import org.greenrobot.eventbus.EventBus;

public class MoreStoreViewModel extends CustomViewModel {

    private ActivityMoreStoreBinding binding;
    private SelectStoreAdapter adapter;
    private StoreBean bean;

    public MoreStoreViewModel(CustomActivity mActivity, ActivityMoreStoreBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new SelectStoreAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //通知选择成功
            EventBus.getDefault().postSticky(new MessageEvent(bean.getData().getList().get(position)));
            mActivity.finish();
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
