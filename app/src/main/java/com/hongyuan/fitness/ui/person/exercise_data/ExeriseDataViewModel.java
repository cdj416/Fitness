package com.hongyuan.fitness.ui.person.exercise_data;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityExerciseDataBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.WristbandRealTimeData;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger.WristbandExerciseDataBarManger;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_step.WristbandStepActivity;

public class ExeriseDataViewModel extends CustomViewModel {

    private ActivityExerciseDataBinding binding;
    private ExeriseDataTopBeans.DataBean topBeans;

    private WristbandExerciseDataBarManger dataBarManger;

    //今日时时数据
    private WristbandRealTimeData toadyData;

    public ExeriseDataViewModel(CustomActivity mActivity, ActivityExerciseDataBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        toadyData = WristbandRealTimeData.getInstance();
        binding.allTime.setText(toadyData.getActive()+"min");
        binding.todayStepNum.setRightText(toadyData.getStep()+"");

        dataBarManger = new WristbandExerciseDataBarManger(binding.chart1);

        binding.todayStepNum.setGoClick(() -> {
            startActivity(WristbandStepActivity.class,null);
        });

    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_SPORT_LIST,Controller.TYPE_POST,getParams(), ExeriseDataTopBeans.class,this);

        clearParams();
        Controller.myRequest(Constants.GET_EXERCISE_ITEM_INFO,Controller.TYPE_POST,getParams(), ExeriseDataBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();
        if(data instanceof ExeriseDataTopBeans){
            topBeans = ((ExeriseDataTopBeans)data).getData();

            //数据处理
            if(topBeans.getList() == null || topBeans.getList().size() < 10){
                for(int i = topBeans.getList().size() ; i < 10 ; i++){
                    ExeriseDataTopBeans.DataBean.ListBean listBean = new ExeriseDataTopBeans.DataBean.ListBean();
                    listBean.setM_id(-1);
                    listBean.setRecord_date("");
                    listBean.setSumStep(0);
                    topBeans.getList().add(listBean);
                }
            }else{
                topBeans.setList(topBeans.getList().subList((topBeans.getList().size() - 10),topBeans.getList().size()));
            }

            if(topBeans.getList() != null){
                //设置数据并显示
                dataBarManger.showBarChart(topBeans.getList());
            }

        }

        if(data instanceof ExeriseDataBeans){
            ExeriseDataBeans.DataBean.InfoBean infoBean = ((ExeriseDataBeans)data).getData().getInfo();
            if(infoBean != null){
                binding.allWalk.setTitleName("行走");
                binding.allWalk.setValue1(infoBean.getWalk_distance()+"km");
                binding.allWalk.setValue1Title("总距离");
                binding.allWalk.setValue2(String.valueOf(infoBean.getWalk_num()));

                binding.allRun.setTitleName("跑步");
                binding.allRun.setValue1(infoBean.getRunning_distance()+"km");
                binding.allRun.setValue1Title("总距离");
                binding.allRun.setValue2(String.valueOf(infoBean.getRunning_num()));

                binding.allsports.setTitleName("健身");
                binding.allsports.setValue1(infoBean.getFitness_exerciseTime()+"min");
                binding.allsports.setValue1Title("总时间");
                binding.allsports.setValue2(String.valueOf(infoBean.getFitness_num()));

                binding.allBall.setTitleName("球类运动");
                binding.allBall.setValue1(infoBean.getBall_exerciseTime()+"min");
                binding.allBall.setValue1Title("总时间");
                binding.allBall.setValue2(String.valueOf(infoBean.getBall_num()));

                binding.allSwim.setTitleName("游泳");
                binding.allSwim.setValue1(infoBean.getSwim_exerciseTime()+"min");
                binding.allSwim.setValue1Title("总时间");
                binding.allSwim.setValue2(String.valueOf(infoBean.getSwim_num()));

                //int allNum = infoBean.getWalk_exerciseTime()
                //binding.allTime.setText(TimeUtil.getTime());
            }
        }
    }
}
