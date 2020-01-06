package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.wristband_data_detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.custom_view.BarPercentView;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.WristbandHistoryUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.WristbandRealTimeData;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger.CubicLineChartManger;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_heart_rate.WristbandHeartRateActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep.WristbandSleepActivity;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_step.WristbandStepActivity;
import com.hongyuan.fitness.ui.person.exercise_data.ExeriseDataActivity;
import com.hongyuan.fitness.util.TimeUtil;
import com.yolanda.health.qnblesdk.bean.QNExercise;
import com.yolanda.health.qnblesdk.bean.QNHeartRate;
import com.yolanda.health.qnblesdk.bean.QNRealTimeData;
import com.yolanda.health.qnblesdk.bean.QNSleep;
import com.yolanda.health.qnblesdk.bean.QNSport;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.constant.QNHealthDataType;
import com.yolanda.health.qnblesdk.listener.QNObjCallback;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;
import java.util.List;

public class WristbandDeviceDataFragment extends CustomFragment {

    //青牛手环sdk
    //蓝牙扫描类
    private QNBleApi mQNBleApi;
    //手环命令管理器
    private QNBandManager bandManager;
    //手环连接状态
    private WristbandStautsUtils stautsUtils;
    //手环历史数据存储记录类
    private WristbandHistoryUtils historyUtils;
    //心率曲线图配置管理器
    private CubicLineChartManger lineChartManger;

    private TextView wPower,todayDate,kcalNum,todayStepNum,exerciseDuration,heartRate,heartRateNum
            ,allSleepH,allSleepS,sumDeepSleepText,sumLightSleepText,sumSoberText;
    private BarPercentView barDeepSleep,barLightSleep,barSober;
    private LinearLayout goStepBox,goSleepBox,heartRateBox;
    private RelativeLayout goRunBox;
    private LineChart lineChart;

    //今日睡眠数据
    private QNSleep qnSleep;
    //今日心率
    private QNHeartRate qnHeartRate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wristband_device_data;
    }

    @Override
    public void initView(View mView) {
        setEnableOverScrollDrag(true);

        mQNBleApi = QNBleApi.getInstance(mActivity);
        bandManager = mQNBleApi.getBandManager();
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);
        historyUtils = WristbandHistoryUtils.getInstance();


        wPower = mView.findViewById(R.id.wPower);
        todayDate = mView.findViewById(R.id.todayDate);
        kcalNum = mView.findViewById(R.id.kcalNum);
        todayStepNum = mView.findViewById(R.id.todayStepNum);
        exerciseDuration = mView.findViewById(R.id.exerciseDuration);
        heartRate = mView.findViewById(R.id.heartRate);
        goStepBox = mView.findViewById(R.id.goStepBox);
        heartRateNum = mView.findViewById(R.id.heartRateNum);
        lineChart = mView.findViewById(R.id.lineChart);
        allSleepH = mView.findViewById(R.id.allSleepH);
        allSleepS = mView.findViewById(R.id.allSleepS);
        sumDeepSleepText = mView.findViewById(R.id.sumDeepSleepText);
        sumLightSleepText = mView.findViewById(R.id.sumLightSleepText);
        sumSoberText = mView.findViewById(R.id.sumSoberText);
        barDeepSleep = mView.findViewById(R.id.barDeepSleep);
        barLightSleep = mView.findViewById(R.id.barLightSleep);
        barSober = mView.findViewById(R.id.barSober);
        goSleepBox = mView.findViewById(R.id.goSleepBox);
        heartRateBox = mView.findViewById(R.id.heartRateBox);
        goRunBox = mView.findViewById(R.id.goRunBox);

        //配置曲线图
        lineChartManger = new CubicLineChartManger(lineChart);

        todayDate.setText(TimeUtil.getCurrentDate(TimeUtil.dateFormatYMD));

        if(stautsUtils.isBand()){
            getAllData();
        }

        goStepBox.setOnClickListener(v -> {
            startActivity(WristbandStepActivity.class,null);
        });
        goSleepBox.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("qnSleep",qnSleep);
            startActivity(WristbandSleepActivity.class,bundle);
        });
        heartRateBox.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("qnHeartRate",qnHeartRate);
            startActivity(WristbandHeartRateActivity.class,bundle);
        });
        goRunBox.setOnClickListener(v -> {
            startActivity(ExeriseDataActivity.class,null);
        });

    }

    /*
    * 获取时时数据
    * */
    public void getAllData(){
        mActivity.showLoading();

        //设置电量
        setBattery();

        //获取运动数据
        getTodayHealthData();

        //获取心率数据
        getHeart();

        //获取睡眠数据
        getSleep();

        //记录历史数据
        getAllHistoryData();
    }

    /*
    * 获取手环信息
    * */
    private void setBattery(){
        bandManager.fetchBandInfo((data, code, msg) -> {
            wPower.setText("已连接 "+data.getElectric()+"%");
        });
    }

    /*
    * 获取时时数据
    * */
    private void getTodayHealthData(){
        bandManager.syncRealTimeData((data, code, msg) -> {
            Log.e("cdj","=========code====="+code+"=======msg===="+msg);
            if(data != null){
                //赋值给单例
                setRelTimeData(data);
                //初始化值
                kcalNum.setText(String.valueOf(data.getCalories()));
                todayStepNum.setText(String.valueOf(data.getStep()));
                exerciseDuration.setText(TimeUtil.getTime(data.getActive()*60));
                heartRate.setText(String.valueOf(data.getHeartRate()));
            }
        });
    }

    /*
    * 把数据存储给单例类
    * */
    private void setRelTimeData(QNRealTimeData relTimeData){
        WristbandRealTimeData myRelTimeData = WristbandRealTimeData.getInstance();
        myRelTimeData.setStep(relTimeData.getStep());
        myRelTimeData.setActive(relTimeData.getActive());
        myRelTimeData.setCalories(relTimeData.getCalories());
        myRelTimeData.setDistance(relTimeData.getDistance());
        myRelTimeData.setHeartRate(relTimeData.getHeartRate());
        myRelTimeData.setSleep(relTimeData.getSleep());
    }

    /*
    * 获取心率
    * */
    private void getHeart(){
        bandManager.syncTodayHealthData(QNHealthDataType.HEALTH_DATA_TYPE_HEART, (QNObjCallback<QNHeartRate>) (data, code, msg) -> {
            if (code == CheckStatus.OK.getCode() && data != null) {
                qnHeartRate = data;
                heartRateNum.setText(String.valueOf(data.getSmoothHeartRate()));
                lineChartManger.setData(data.getHeartRateItems());
                //设置背景填充色
                Drawable drawable = getResources().getDrawable(R.drawable.shape_gradient_v_ff7e8d_ffffff);
                lineChartManger.setChartFillDrawable(drawable);
            }

            mActivity.closeLoading();
        });

    }

    /*
    * 获取睡眠数据
    * */
    private void getSleep(){
        bandManager.syncTodayHealthData(QNHealthDataType.HEALTH_DATA_TYPE_SLEEP, (QNObjCallback<QNSleep>) (data, code, msg) -> {
            if (code == CheckStatus.OK.getCode()) {
                if (null != data) {
                    qnSleep = data;

                    allSleepH.setText(String.valueOf(data.getSumSleepMinute()/60));
                    allSleepS.setText(String.valueOf(data.getSumSleepMinute()%60));
                    sumDeepSleepText.setText((data.getSumDeepSleepMinute()/60)+"小时"+(data.getSumDeepSleepMinute()%60)+"分钟");
                    sumLightSleepText.setText((data.getSumLightSleepMinute()/60)+"小时"+(data.getSumLightSleepMinute()%60)+"分钟");
                    sumSoberText.setText((data.getSumSoberMinute()/60)+"小时"+(data.getSumSoberMinute()%60)+"分钟");

                    barDeepSleep.setValue(data.getSumSleepMinute(),Float.valueOf(data.getSumDeepSleepMinute()));
                    barLightSleep.setValue(data.getSumSleepMinute(),Float.valueOf(data.getSumLightSleepMinute()));
                    barSober.setValue(data.getSumSleepMinute(),Float.valueOf(data.getSumSoberMinute()));
                } else {
                    Log.e("cdj","===========没有睡眠数据=======");
                }

            }
        });
    }

    /*
    * 从手环中获取所需的所有历史数据
    * */
    private void getAllHistoryData(){

        //上传睡眠历史数据
        if(!historyUtils.isSleep()){
            bandManager.syncHistoryHealthData(QNHealthDataType.HEALTH_DATA_TYPE_SLEEP, (QNObjCallback<List<QNSleep>>) (datas, code, msg) -> {
                if (code == CheckStatus.OK.getCode() && datas != null && !"[]".equals(datas.toString())) {

                    Log.e("cdj","=======获取到的睡眠历史数据====="+datas.toString());
                    clearParams().setParams("sleep_str",historyUtils.getSleep(datas));
                    Controller.myRequest(ConstantsCode.ADD_SLEEP_DAY,Constants.ADD_SLEEP_DAY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
                }else{
                    Log.e("cdj","=======获取到的睡眠历史数据====="+datas);
                }
            });
        }

        //上传心率历史数据
        if(!historyUtils.isHeartRate()){
            bandManager.syncHistoryHealthData(QNHealthDataType.HEALTH_DATA_TYPE_HEART, (QNObjCallback<List<QNHeartRate>>) (datas, code, msg) -> {
                if (code == CheckStatus.OK.getCode() && datas != null && !"[]".equals(datas.toString())) {

                    Log.e("cdj","=======获取到的心率历史数据====="+datas.toString());
                    clearParams().setParams("heart_rate_str",historyUtils.getHeartRate(datas));
                    Controller.myRequest(ConstantsCode.ADD_HEAART_RATE_DAY,Constants.ADD_HEAART_RATE_DAY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
                }else{
                    Log.e("cdj","=======获取到的心率历史数据====="+datas);
                }
            });
        }

        //上传运动历史数据
        if(!historyUtils.isHeartRate()){
            bandManager.syncHistoryHealthData(QNHealthDataType.HEALTH_DATA_TYPE_SPORT, (QNObjCallback<List<QNSport>>) (datas, code, msg) -> {
                if (code == CheckStatus.OK.getCode() && datas != null && !"[]".equals(datas.toString())) {

                    Log.e("cdj","=======获取到的运动历史数据====="+datas.toString());
                    clearParams().setParams("sport_str",historyUtils.getSport(datas));
                    Controller.myRequest(ConstantsCode.ADD_SPORT_DAY,Constants.ADD_SPORT_DAY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
                }else{
                    Log.e("cdj","=======获取到的运动历史数据====="+datas);
                }
            });
        }

        //上传健身历史数据
        if(!historyUtils.isExercise()){
            bandManager.syncHistoryHealthData(QNHealthDataType.HEALTH_DATA_TYPE_FITNESS, (QNObjCallback<List<QNExercise>>) (datas, code, msg) -> {
                if (code == CheckStatus.OK.getCode() && datas != null && !"[]".equals(datas.toString())) {

                    Log.e("cdj","=======获取到的健身历史数据====="+datas.toString());
                    clearParams().setParams("exercise_str",historyUtils.getExercise(datas));
                    Controller.myRequest(ConstantsCode.ADD_EXERCISE_DAY,Constants.ADD_EXERCISE_DAY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
                }else{
                    Log.e("cdj","=======获取到的健身历史数据====="+datas);
                }
            });
        }

    }


    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.ADD_SLEEP_DAY){
            historyUtils.setSleep(true);
        }
        if(code == ConstantsCode.ADD_HEAART_RATE_DAY){
            historyUtils.setHeartRate(true);
        }
        if(code == ConstantsCode.ADD_SPORT_DAY){
            historyUtils.setSport(true);
            Log.e("cdj","=======运动数据上传完成=====");
        }
        if(code == ConstantsCode.ADD_EXERCISE_DAY){
            historyUtils.setExercise(true);
            Log.e("cdj","=======健身数据上传完成=====");
        }
    }
}
