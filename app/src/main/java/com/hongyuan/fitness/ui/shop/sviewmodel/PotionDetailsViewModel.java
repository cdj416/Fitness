package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPoitionDetailsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.PotionDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.PotionDetailsBeans;

import java.util.List;

public class PotionDetailsViewModel extends CustomViewModel {

    private ActivityPoitionDetailsBinding binding;
    private PotionDetailsAdapter adapter;

    private List<PotionDetailsBeans.DataBean.ListBean> mList;

    public PotionDetailsViewModel(CustomActivity mActivity, ActivityPoitionDetailsBinding binding) {
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
        adapter = new PotionDetailsAdapter();
        binding.mRec.setAdapter(adapter);
    }

    @Override
    public void refreshData(){
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
        Controller.myRequest(Constants.GET_POINT_LOG_LIST,Controller.TYPE_POST,getParams(), PotionDetailsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PotionDetailsBeans){

            List<PotionDetailsBeans.DataBean.ListBean> list = ((PotionDetailsBeans)data).getData().getList();
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
