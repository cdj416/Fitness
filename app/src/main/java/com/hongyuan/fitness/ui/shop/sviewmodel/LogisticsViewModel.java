package com.hongyuan.fitness.ui.shop.sviewmodel;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityLogisticsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.LogisticsAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.LogisticsBeans;
public class LogisticsViewModel extends CustomViewModel {

    private ActivityLogisticsBinding binding;

    private LogisticsAdapter adapter;

    public LogisticsViewModel(CustomActivity mActivity, ActivityLogisticsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new LogisticsAdapter();
        binding.mRec.setAdapter(adapter);
        adapter.setFooterView(mActivity.getFooterHeight(binding.mRec));
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("deliver_company_code",getBundle().getString("deliver_company_code"))
                .setParams("deliver_num",getBundle().getString("deliver_num"));
        Controller.myRequest(Constants.GET_DELIVER_INFO,Controller.TYPE_POST,getParams(), LogisticsBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof LogisticsBeans){
            LogisticsBeans.DataBean dataBean = ((LogisticsBeans)data).getData();
            if(dataBean.getList() != null && dataBean.getList().size() > 0){
                adapter.setNewData(dataBean.getList());
            }else{
                mActivity.setPromtView(CustomActivity.SHOW_EMPTY);
            }

        }
    }
}
