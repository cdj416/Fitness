package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_step;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWristbandStepBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.WristbandRealTimeData;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger.WristbandStepBarManger;
import com.hongyuan.fitness.ui.person.exercise_data.ExeriseDataTopBeans;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.List;

public class WristbandStepViewModel extends CustomViewModel {

    private ActivityWristbandStepBinding binding;

    //柱状图管理器
    private WristbandStepBarManger barManger;
    private ExeriseDataTopBeans.DataBean topBeans;
    //实时数据
    private WristbandRealTimeData todayData;

    //最高步数值
    private int maxStepNum = 0;
    //最高步数日期
    private String maxStepTime = "";

    public WristbandStepViewModel(CustomActivity mActivity, ActivityWristbandStepBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);
        todayData = WristbandRealTimeData.getInstance();
        barManger = new WristbandStepBarManger(binding.chart1);
    }

    @Override
    protected void lazyLoad() {
        mActivity.closeLoading();
        clearParams();
        Controller.myRequest(Constants.GET_SPORT_LIST,Controller.TYPE_POST,getParams(), ExeriseDataTopBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof ExeriseDataTopBeans){
            topBeans = ((ExeriseDataTopBeans)data).getData();

            if(todayData != null){
                ExeriseDataTopBeans.DataBean.ListBean todayBean = new ExeriseDataTopBeans.DataBean.ListBean();
                todayBean.setSumStep(todayData.getStep());
                todayBean.setSumCalories(todayData.getCalories());
                todayBean.setSumActiveTime(todayData.getActive());
                todayBean.setSport_id(-1);
                todayBean.setRecord_time((int)(System.currentTimeMillis()/1000));
                todayBean.setRecord_date(TimeUtil.getCurrentDate(TimeUtil.dateFormat));
                todayBean.setM_id(-1);
                todayBean.setSumDistance(todayData.getDistance());

                topBeans.getList().add(todayBean);
            }


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
                barManger.showBarChart(topBeans.getList());
            }

            //遍历最高步数
            getMax(topBeans.getList());

            binding.allStepNum.setText(String.valueOf(todayData.getStep()));
            binding.stepHighest.setText(String.valueOf(maxStepNum));
            binding.dataHighest.setText(TimeUtil.formatDate(maxStepTime,TimeUtil.dateFormat,TimeUtil.dateFormatMDofChinese));
        }
    }

    /*
    * 获取最高步数
    * */
    private void getMax(List<ExeriseDataTopBeans.DataBean.ListBean> mList){

        for(ExeriseDataTopBeans.DataBean.ListBean listBean : mList){
            if(maxStepNum < listBean.getSumStep()){
                maxStepNum = listBean.getSumStep();
                maxStepTime = listBean.getRecord_date();
            }
        }
    }
}
