package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock;

import android.widget.Switch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class WristbandAlarmClockAdapter extends BaseQuickAdapter<AlarmClockBeans.DataBean.ListBean, BaseViewHolder> {

    public WristbandAlarmClockAdapter(){
        super(R.layout.item_wristband_alarm_clock);
    }

    @Override
    protected void convert(BaseViewHolder helper, AlarmClockBeans.DataBean.ListBean item) {
        helper.setText(R.id.clockTime,item.getHour()+":"+item.getMinture())
                .setText(R.id.weekTime,getWeeks(item));

        Switch switchBt = helper.getView(R.id.switchBt);

        switchBt.setChecked(item.getOpenFlag() == 1);

        helper.addOnClickListener(R.id.box).addOnClickListener(R.id.switchBt);
    }

    /*
    * 组装闹钟星期数据
    * */
    private String getWeeks(AlarmClockBeans.DataBean.ListBean qnWeek){
        String showText = "";
        if(qnWeek.getMon() == 1){
            showText += "周一,";
        }
        if(qnWeek.getTues() == 1){
            showText += "周二,";
        }
        if(qnWeek.getWed() == 1){
            showText += "周三,";
        }
        if(qnWeek.getThur() == 1){
            showText += "周四,";
        }
        if(qnWeek.getFri() == 1){
            showText += "周五,";
        }
        if(qnWeek.getSat() == 1){
            showText += "周六,";
        }
        if(qnWeek.getSun() == 1){
            showText += "周日";
        }

        return showText;
    }
}
