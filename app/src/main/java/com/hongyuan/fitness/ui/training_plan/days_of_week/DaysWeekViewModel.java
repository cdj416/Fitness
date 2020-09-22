package com.hongyuan.fitness.ui.training_plan.days_of_week;

import android.os.Bundle;
import android.widget.SeekBar;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityDaysOfWeekBinding;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanDataBeans;
import com.hongyuan.fitness.ui.training_plan.fitness_goal.FitnessGoaltActivity;
import com.hongyuan.fitness.ui.training_plan.fitness_goal.FitnessGoaltBeans;
import com.hongyuan.fitness.ui.training_plan.plan_details.PlanDetailsActivity;

public class DaysWeekViewModel extends CustomViewModel {

    private ActivityDaysOfWeekBinding binding;

    //数据传递对象
    private TrainingPlanDataBeans useData;

    public DaysWeekViewModel(CustomActivity mActivity, ActivityDaysOfWeekBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        useData = (TrainingPlanDataBeans) getBundle().getSerializable("useData");

        binding.whText.setText(useData.getHeight()+"cm / "+useData.getWeight()+"kg");
        binding.planText.setText(useData.getPlanMbName());
        binding.planWeightText.setText(useData.getPlanWeight()+"kg");
        useData.setPlanDay("4");

        binding.addBoxBut.setOnClickListener(v -> {
            addPlan();

        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.daysNum.setText(progress+"");
                useData.setPlanDay(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /*
    * 添加个人计划
    * */
    private void addPlan(){
        mActivity.showLoading();
        clearParams().setParams("plan_mb",useData.getPlanMb()).setParams("plan_weight",useData.getPlanWeight())
                .setParams("plan_day",useData.getPlanDay());

        Controller.myRequest(ConstantsCode.ADD_PLAN,Constants.ADD_PLAN,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_PLAN){
            startActivity(PlanDetailsActivity.class,null);
        }
    }
}
