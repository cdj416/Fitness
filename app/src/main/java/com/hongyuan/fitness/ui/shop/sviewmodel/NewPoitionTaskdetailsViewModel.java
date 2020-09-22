package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityNewpoitionTaskdetailsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.PotionTaskDetailsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.MyTaskBeans;
import com.hongyuan.fitness.util.JumpUtils;

import java.util.List;

public class NewPoitionTaskdetailsViewModel extends CustomViewModel {

    private ActivityNewpoitionTaskdetailsBinding binding;
    //任务列表
    private PotionTaskDetailsAdapter adapter;
    private List<MyTaskBeans.DataBean.ListBean> mList;

    public NewPoitionTaskdetailsViewModel(CustomActivity mActivity, ActivityNewpoitionTaskdetailsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {


        //setEnableLoadMore(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new PotionTaskDetailsAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            JumpUtils.goAtherPage(mActivity,mList.get(position).getPt_code());
        });

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
        clearParams();
        Controller.myRequest(Constants.MY_TASK,Controller.TYPE_POST,getParams(), MyTaskBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof MyTaskBeans){

            List<MyTaskBeans.DataBean.ListBean> list = ((MyTaskBeans)data).getData().getList();
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
