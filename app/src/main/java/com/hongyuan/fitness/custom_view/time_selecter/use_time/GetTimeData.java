package com.hongyuan.fitness.custom_view.time_selecter.use_time;

import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.time_selecter.OnTimeSelectListener;
import com.hongyuan.fitness.custom_view.time_selecter.TimePickerBuilder;
import com.hongyuan.fitness.custom_view.time_selecter.TimePickerView;
import com.hongyuan.fitness.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;

public class GetTimeData {
    private CustomActivity mActivity;

    private TimePickerView pvTime;

    public GetTimeData(CustomActivity mActivity, OnTimeSelectListener selectListener, Date selectDate, Date endData, boolean[] type){
        this.mActivity = mActivity;
        initTimePicker(selectListener,selectDate,endData,type);
    }

    public void showTime(){
        pvTime.show();
    }

    private void initTimePicker(OnTimeSelectListener selectListener, Date selectDate, Date endTime, boolean[] type) {//Dialog 模式下，在底部弹出
        Calendar startData = Calendar.getInstance();
        Calendar selectData = Calendar.getInstance();
        Calendar endData = Calendar.getInstance();
        startData.setTime(TimeUtil.getDateByFormat("1900-01-01",TimeUtil.dateFormatYMD));
        selectData.setTime(selectDate);
        endData.setTime(endTime);
        endData.add(Calendar.DATE, 1);

        pvTime = new TimePickerBuilder(mActivity,selectListener)
                .setTimeSelectChangeListener(date -> Log.i("pvTime", "onTimeSelectChanged"))
                .setType(type)
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(view -> Log.i("pvTime", "onCancelClickListener"))
                .setRangDate(startData,endData)
                .setDate(selectData)
                .build();
        pvTime.setTitleText("选择日期");
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.hongyuan.fitness.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.1f);
            }
        }
    }

    /**************************************枚举类型***********************************************/

    public static final boolean[] YMD = {true,true,true,false,false,false};
    public static final boolean[] YMDHM = {true,true,true,true,true,false};
    public static final boolean[] YMDHMS = {true,true,true,true,true,true};
}
