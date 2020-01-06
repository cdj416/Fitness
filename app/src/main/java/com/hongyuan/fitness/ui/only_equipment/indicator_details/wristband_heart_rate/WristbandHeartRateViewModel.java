package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_heart_rate;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityHeartRateBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger.HeartLineChartManger;
import com.yolanda.health.qnblesdk.bean.QNHeartRate;
import com.yolanda.health.qnblesdk.bean.QNHeartRateItem;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.constant.QNHealthDataType;
import com.yolanda.health.qnblesdk.listener.QNObjCallback;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;

import java.util.List;

public class WristbandHeartRateViewModel extends CustomViewModel {

    private ActivityHeartRateBinding binding;

    //心率曲线图配置管理器
    private HeartLineChartManger lineChartManger;

    //获取今日心率
    private QNHeartRate qnHeartRate;

    public WristbandHeartRateViewModel(CustomActivity mActivity, ActivityHeartRateBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        qnHeartRate = getBundle().getParcelable("qnHeartRate");
        //配置曲线图
        lineChartManger = new HeartLineChartManger(binding.lineChart);

        if(qnHeartRate != null){
            lineChartManger.setData(qnHeartRate.getHeartRateItems());

            binding.highestNum.setText(String.valueOf(getHighest(qnHeartRate.getHeartRateItems())));
            binding.lowestNum.setText(String.valueOf(getLowest(qnHeartRate.getHeartRateItems())));
            binding.averageNum.setText(String.valueOf(qnHeartRate.getSmoothHeartRate()));

            //设置背景填充色
            //Drawable drawable = getResources().getDrawable(R.drawable.shape_gradient_v_ff7e8d_ffffff);
            //lineChartManger.setChartFillDrawable(drawable);
        }
    }

    /*
    * 获取最高心率
    * */
    private int getHighest(List<QNHeartRateItem> mList){
        int hightNum = 0;
        for(QNHeartRateItem item : mList){
            if(hightNum < item.getHeartRate()){
                hightNum = item.getHeartRate();
            }
        }

        return hightNum;
    }
    /*
    * 获取最低心率
    * */
    private int getLowest(List<QNHeartRateItem> mList){
        int lowestNum = 1000;
        for(QNHeartRateItem item : mList){
            if(lowestNum > item.getHeartRate()){
                lowestNum = item.getHeartRate();
            }
        }

        return lowestNum;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
