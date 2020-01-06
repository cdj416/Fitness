package com.hongyuan.fitness.ui.training_plan.basic_information;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.MyRulerView;
import com.hongyuan.fitness.custom_view.time_selecter.time_data.GetTimeData;
import com.hongyuan.fitness.databinding.ActivityBasicInformationBinding;
import com.hongyuan.fitness.ui.training_plan.TrainingPlanDataBeans;
import com.hongyuan.fitness.ui.training_plan.fitness_goal.FitnessGoaltActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.TimeUtil;
public class BasicInformationViewModel extends CustomViewModel implements MyRulerView.SelectChange {

    private ActivityBasicInformationBinding binding;

    //标识身高
    private final int HEIGHT_RUBLER = 0X2;
    //标识体重
    private final int WEIGHT_RUBLER = 0X3;

    //标识男
    private final int MAN_CLICK = 1;
    //标识nv
    private final int WOMAN_CLICK = 2;
    //标识当前点击值
    private int click_num = MAN_CLICK;
    //放大动画
    private Animation amplification;
    //缩小动画
    private Animation narrow;
    //时间选择器
    private GetTimeData timeData;

    //数据传递对象
    private TrainingPlanDataBeans useData;

    public BasicInformationViewModel(CustomActivity mActivity, ActivityBasicInformationBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        useData = new TrainingPlanDataBeans();
        amplification = AnimationUtils.loadAnimation(mActivity, R.anim.scale_amplification);
        narrow = AnimationUtils.loadAnimation(mActivity,R.anim.scale_narrow);

        //初始化时间选择器
        timeData = new GetTimeData(mActivity, (date, v) -> {
            useData.setBothirdDay(TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD));
            binding.selectDays.setText(TimeUtil.getStringByFormat(date,TimeUtil.dateFormatYMD));
        });
        //初始化默认选中值
        changeSex(click_num);

        binding.heightRuler.setDataRuler(HEIGHT_RUBLER,180,0,300,this);
        binding.bodyWeightRuler.setDataRuler(WEIGHT_RUBLER,70,0,300,this);
        binding.clickMan.setOnClickListener(v -> {
            if(click_num != MAN_CLICK){
                click_num = MAN_CLICK;
                changeSex(click_num);
            }
        });
        binding.clickWoman.setOnClickListener(v -> {
            if(click_num != WOMAN_CLICK){
                click_num = WOMAN_CLICK;
                changeSex(click_num);
            }
        });
        binding.selectDays.setOnClickListener(v -> timeData.showTime());

        binding.addBoxBut.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(!BaseUtil.isValue(useData.getBothirdDay())){
                    CustomDialog.showMessage(mActivity,"请选择生日！");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("useData",useData);
                startActivity(FitnessGoaltActivity.class,bundle);
            }
        });


        //初始化基本信息
        useData.setSex("男");
        useData.setHeight("180");
        useData.setWeight("70");
    }


    @Override
    public void valueChange(int whichCode, float value) {
        if(whichCode == HEIGHT_RUBLER){
            binding.heightNum.setText(BaseUtil.getNoZoon(value));
            useData.setHeight(BaseUtil.getNoZoon(value));
        }
        if(whichCode == WEIGHT_RUBLER){
            binding.weightNum.setText(BaseUtil.getNoZoon(value));
            useData.setWeight(BaseUtil.getNoZoon(value));
        }
    }

    /*
     * 改变性别选择效果状态
     * */
    private void changeSex(int click_num){
        if(click_num == MAN_CLICK){
            binding.clickMan.startAnimation(amplification);
            binding.clickWoman.startAnimation(narrow);
            binding.clickMan.setTextColor(0xFFEF5B48);
            binding.clickWoman.setTextColor(0xFF999999);
            useData.setSex("男");
        }
        if(click_num == WOMAN_CLICK){
            binding.clickMan.startAnimation(narrow);
            binding.clickWoman.startAnimation(amplification);
            binding.clickMan.setTextColor(0xFF999999);
            binding.clickWoman.setTextColor(0xFFEF5B48);
            useData.setSex("女");
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
