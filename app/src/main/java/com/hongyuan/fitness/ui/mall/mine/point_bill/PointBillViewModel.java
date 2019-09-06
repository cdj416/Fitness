package com.hongyuan.fitness.ui.mall.mine.point_bill;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPointBillBinding;

public class PointBillViewModel extends CustomViewModel {

    private ActivityPointBillBinding billBinding;
    private PointBillAdapter adapter;
    private PointBillBean pointBillBean;

    public PointBillViewModel(CustomActivity mActivity, ActivityPointBillBinding billBinding) {
        super(mActivity);
        this.billBinding = billBinding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager menuManager = new LinearLayoutManager(mActivity);
        menuManager.setOrientation(RecyclerView.VERTICAL);
        billBinding.mRecycler.setLayoutManager(menuManager);
        adapter = new PointBillAdapter();
        billBinding.mRecycler.setAdapter(adapter);
    }

    @Override
    public void refreshData(){
        pointBillBean = null;
        lazyLoad();
    }

    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_POINT_LOG_LIST,Controller.TYPE_POST,getParams(), PointBillBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBillBean){
            PointBillBean pageData = (PointBillBean)data;
            if(curPage == FIRST_PAGE){
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    pointBillBean = pageData;
                }
            }else{
                if(pageData.getData().getList() != null && pageData.getData().getList().size() > 0){
                    pointBillBean.getData().getList().addAll(pageData.getData().getList());
                }
            }

            if(pointBillBean != null && pointBillBean.getData() != null &&
                    pointBillBean.getData().getList() != null &&
                    pointBillBean.getData().getList().size() > 0){
                adapter.setNewData(pointBillBean.getData().getList());
                mActivity.setPromtView(mActivity.SHOW_DATA);
            }else{
                mActivity.setPromtView(mActivity.SHOW_EMPTY);
            }
        }
    }
}
