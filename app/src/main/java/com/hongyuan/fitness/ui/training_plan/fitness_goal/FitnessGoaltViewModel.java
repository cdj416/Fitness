package com.hongyuan.fitness.ui.training_plan.fitness_goal;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityFitnessGoalBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.ExaminationDetailsBeans;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanDataBeans;
import com.hongyuan.fitness.ui.training_plan.target_weight.TargetWeightActivity;
import com.hongyuan.fitness.util.DividerItemDecoration;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class FitnessGoaltViewModel extends CustomViewModel {

    private ActivityFitnessGoalBinding binding;
    private FitnessGoaltAdapter adapter;
    private List<FitnessGoaltBeans.DataBean> goaltBeans;

    //数据传递对象
    private TrainingPlanDataBeans useData;

    public FitnessGoaltViewModel(CustomActivity mActivity, ActivityFitnessGoalBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        useData = (TrainingPlanDataBeans) getBundle().getSerializable("useData");

        binding.showDataText.setText(useData.getSex()+" / "+
                TimeUtil.getAgeByBirthday(TimeUtil.formatDate(useData.getBothirdDay()))+" / " +
                useData.getHeight()+"cm / "+useData.getWeight()+"kg");

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(
                mActivity, DividerItemDecoration.HORIZONTAL_LIST,16,0x00000000));
        binding.mRecycler.setLayoutManager(manager);
        adapter = new FitnessGoaltAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            for(FitnessGoaltBeans.DataBean dataBean : goaltBeans){
                dataBean.setSelect(false);
            }
            goaltBeans.get(position).setSelect(true);
            useData.setPlanMb(String.valueOf(goaltBeans.get(position).getId()));
            adapter.notifyDataSetChanged();
            useData.setPlanMb(String.valueOf(goaltBeans.get(position).getId()));
            useData.setPlanMbName(String.valueOf(goaltBeans.get(position).getName()));
        });


        binding.addBoxBut.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("useData",useData);
            startActivity(TargetWeightActivity.class,bundle);
        });


    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_PLAN_MB,Controller.TYPE_POST,getParams(), FitnessGoaltBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof FitnessGoaltBeans){
            goaltBeans = ((FitnessGoaltBeans)data).getData();
            if(goaltBeans != null && goaltBeans.size() > 0){
                goaltBeans.get(0).setSelect(true);
                useData.setPlanMb(String.valueOf(goaltBeans.get(0).getId()));
                useData.setPlanMbName(String.valueOf(goaltBeans.get(0).getName()));
                adapter.setNewData(goaltBeans);
            }
        }
    }
}
