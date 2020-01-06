package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_sleep;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWristbandDeviceSleepBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.mp_chart_manger.SleepBarManger;
import com.hongyuan.fitness.util.TimeUtil;
import com.yolanda.health.qnblesdk.bean.QNSleep;

public class WristbandSleepViewModel extends CustomViewModel {

    private ActivityWristbandDeviceSleepBinding binding;

    private SleepBarManger barManger;

    //传递过来的睡眠数据
    private QNSleep qnSleep;

    public WristbandSleepViewModel(CustomActivity mActivity, ActivityWristbandDeviceSleepBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);
        qnSleep = getBundle().getParcelable("qnSleep");

        barManger = new SleepBarManger(binding.chart1);

        if(qnSleep != null){
            binding.allHour.setText(String.valueOf(qnSleep.getSumSleepMinute()/60));
            binding.allMinet.setText(String.valueOf(qnSleep.getSumSleepMinute()%60));
            binding.dataText.setText(TimeUtil.getCurrentDate(TimeUtil.dateFormatMDofChinese)+"，睡眠时长");
            binding.sumDeepSleepText.setText((qnSleep.getSumDeepSleepMinute()/60)+"小时"+(qnSleep.getSumDeepSleepMinute()%60)+"分钟");
            binding.sumLightSleepText.setText((qnSleep.getSumLightSleepMinute()/60)+"小时"+(qnSleep.getSumLightSleepMinute()%60)+"分钟");
            binding.sumSoberText.setText((qnSleep.getSumSoberMinute()/60)+"小时"+(qnSleep.getSumSoberMinute()%60)+"分钟");

            binding.barDeepSleep.setValue(qnSleep.getSumSleepMinute(),Float.valueOf(qnSleep.getSumDeepSleepMinute()));
            binding.barLightSleep.setValue(qnSleep.getSumSleepMinute(),Float.valueOf(qnSleep.getSumLightSleepMinute()));
            binding.barSober.setValue(qnSleep.getSumSleepMinute(),Float.valueOf(qnSleep.getSumSoberMinute()));
        }
    }

    @Override
    protected void lazyLoad() {
        mActivity.closeLoading();
        clearParams();
        Controller.myRequest(Constants.GET_SLEEP_LIST,Controller.TYPE_POST,getParams(), WristbandSleepBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof WristbandSleepBeans){
            WristbandSleepBeans.DataBean dataBean = ((WristbandSleepBeans)data).getData();

            //数据处理
            if(dataBean.getList() == null || dataBean.getList().size() < 10){
                for(int i = dataBean.getList().size() ; i < 10 ; i++){
                    WristbandSleepBeans.DataBean.ListBean listBean = new WristbandSleepBeans.DataBean.ListBean();
                    dataBean.getList().add(listBean);
                }
            }else{
                dataBean.setList(dataBean.getList().subList((dataBean.getList().size() - 10),dataBean.getList().size()));
            }
            barManger.showBarChart(dataBean.getList(),mActivity);
        }
    }
}
