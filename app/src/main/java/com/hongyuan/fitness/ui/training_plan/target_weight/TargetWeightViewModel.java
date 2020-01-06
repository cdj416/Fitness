package com.hongyuan.fitness.ui.training_plan.target_weight;

import android.os.Bundle;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.MyRulerView;
import com.hongyuan.fitness.databinding.ActivityTargetWeightBinding;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanDataBeans;
import com.hongyuan.fitness.ui.training_plan.days_of_week.DaysOfWeekActivity;
import com.hongyuan.fitness.util.BaseUtil;

public class TargetWeightViewModel extends CustomViewModel implements MyRulerView.SelectChange  {

    //标识体重
    private final int WEIGHT_RUBLER = 0X3;

    private ActivityTargetWeightBinding binding;
    //数据传递对象
    private TrainingPlanDataBeans useData;

    public TargetWeightViewModel(CustomActivity mActivity, ActivityTargetWeightBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        useData = (TrainingPlanDataBeans) getBundle().getSerializable("useData");

        binding.bodyWeightRuler.setDataRuler(WEIGHT_RUBLER,70,0,300,this);

        binding.addBoxBut.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("useData",useData);
            startActivity(DaysOfWeekActivity.class,bundle);
        } );

        binding.whText.setText(useData.getHeight()+"cm / "+useData.getWeight()+"kg");
        binding.planText.setText(useData.getPlanMbName());
        useData.setPlanWeight("70");
    }

    @Override
    public void valueChange(int whichCode, float value) {
        if(whichCode == WEIGHT_RUBLER){
            binding.weightNum.setText(BaseUtil.getNoZoon(value));
            useData.setPlanWeight(BaseUtil.getNoZoon(value));
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
