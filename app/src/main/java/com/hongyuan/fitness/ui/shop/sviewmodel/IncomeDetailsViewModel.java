package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIncomeDetailsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.IncomeDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.ui.shop.sbeans.IncomeDetailsBeans;

import java.util.List;

public class IncomeDetailsViewModel extends CustomViewModel {

    private ActivityIncomeDetailsBinding binding;
    private IncomeDetailsAdapter adapter;

    //商品数据
    private List<IncomeDetailsBeans.DataBean.ListBean> mList;

    public IncomeDetailsViewModel(CustomActivity mActivity, ActivityIncomeDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new IncomeDetailsAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));

    }


    @Override
    protected void loadMoreData() {
        lazyLoad();
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_SETTLEMENT_LIST,Controller.TYPE_POST,getParams(), IncomeDetailsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof IncomeDetailsBeans){
            List<IncomeDetailsBeans.DataBean.ListBean> list = ((IncomeDetailsBeans)data).getData().getList();
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
