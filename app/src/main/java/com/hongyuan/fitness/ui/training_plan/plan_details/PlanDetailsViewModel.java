package com.hongyuan.fitness.ui.training_plan.plan_details;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPlanDetailsBinding;
import com.hongyuan.fitness.ui.training_plan.basic_information.BasicInformationActivity;
import com.hongyuan.fitness.ui.training_plan.plan_program.PlansProgramActivity;

import java.util.List;

public class PlanDetailsViewModel extends CustomViewModel {

    private ActivityPlanDetailsBinding binding;
    private PlanDetailsStateBeans.DataBean.InfoBean dataBean;

    private List<PlanDetailsContentBeans.DataBean.ListBean> contentList;
    private PlanDetailstAdapter adapter;
    public PlanDetailsViewModel(CustomActivity mActivity, ActivityPlanDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);
        setEnableRefresh(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(manager);
        adapter = new PlanDetailstAdapter();
        binding.mRecycler.setAdapter(adapter);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("content_id",String.valueOf(contentList.get(position).getContent_id()));
            startActivity(PlansProgramActivity.class,bundle);
        });

        binding.goRedesigned.setOnClickListener(v -> {
            startActivity(BasicInformationActivity.class,null);
        });
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        //计划--用户计划状态
        clearParams();
        Controller.myRequest(Constants.GET_PLAN_STATE,Controller.TYPE_POST,getParams(), PlanDetailsStateBeans.class,this);

    }

    /*
    * 计划--教练已完成计划列表
    * */
    private void getPlanList(String plan_id){
        //计划--计划列表
        clearParams().setParams("plan_id",plan_id);
        Controller.myRequest(Constants.GET_PLAN_CONTENT_LIST,Controller.TYPE_POST,getParams(), PlanDetailsContentBeans.class,this);
    }

    @Override
    protected void setData() {
        binding.whText.setText(dataBean.getBody_index().getMbi_height()+"cm / "+dataBean.getBody_index().getMbi_weight()+"kg");
        binding.planText.setText(dataBean.getMb_str());
        binding.planWeight.setText(dataBean.getPlan_weight()+"kg");
        binding.daysOfWeek.setText(dataBean.getPlan_day()+"天");
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PlanDetailsStateBeans){
            dataBean = ((PlanDetailsStateBeans)data).getData().getInfo();
            setData();
            getPlanList(String.valueOf(dataBean.getPlan_id()));
        }

        if(data instanceof PlanDetailsContentBeans){
            contentList = ((PlanDetailsContentBeans)data).getData().getList();

            if(contentList != null && contentList.size() > 0){
                binding.noData.setVisibility(View.GONE);
                binding.contentBox.setVisibility(View.VISIBLE);
                adapter.setNewData(contentList);
            }else{
                binding.noData.setVisibility(View.VISIBLE);
                binding.contentBox.setVisibility(View.GONE);
            }

        }
    }
}
