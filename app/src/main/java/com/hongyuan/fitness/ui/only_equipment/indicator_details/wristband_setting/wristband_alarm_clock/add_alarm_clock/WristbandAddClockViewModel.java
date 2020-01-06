package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.add_alarm_clock;

import android.util.Log;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAddWristbandAlarmClockBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.AlarmClockBeans;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.ArrayList;
import java.util.List;

public class WristbandAddClockViewModel extends CustomViewModel {

    private ActivityAddWristbandAlarmClockBinding binding;

    private AlarmClockBeans.DataBean.ListBean clockBeans;

    //初始化选中时间
    private int useHour = 12;
    private int useSecond = 30;

    public WristbandAddClockViewModel(CustomActivity mActivity, ActivityAddWristbandAlarmClockBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        if(getBundle() != null){
            clockBeans = (AlarmClockBeans.DataBean.ListBean) getBundle().getSerializable("item");
        }

        getHour();

        binding.tvSun.setOnClickListener(v -> {
            if("true".equals(binding.tvSun.getTag().toString())){
                binding.tvSun.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvSun.setTag("false");
            }else{
                binding.tvSun.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvSun.setTag("true");
            }
        });
        binding.tvMon.setOnClickListener(v -> {
            if("true".equals(binding.tvMon.getTag().toString())){
                binding.tvMon.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvMon.setTag("false");
            }else{
                binding.tvMon.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvMon.setTag("true");
            }
        });
        binding.tvTues.setOnClickListener(v -> {
            if("true".equals(binding.tvTues.getTag().toString())){
                binding.tvTues.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvTues.setTag("false");
            }else{
                binding.tvTues.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvTues.setTag("true");
            }
        });
        binding.tvWed.setOnClickListener(v -> {
            if("true".equals(binding.tvWed.getTag().toString())){
                binding.tvWed.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvWed.setTag("false");
            }else{
                binding.tvWed.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvWed.setTag("true");
            }
        });
        binding.tvThur.setOnClickListener(v -> {
            if("true".equals(binding.tvThur.getTag().toString())){
                binding.tvThur.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvThur.setTag("false");
            }else{
                binding.tvThur.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvThur.setTag("true");
            }
        });
        binding.tvFri.setOnClickListener(v -> {
            if("true".equals(binding.tvFri.getTag().toString())){
                binding.tvFri.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvFri.setTag("false");
            }else{
                binding.tvFri.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvFri.setTag("true");
            }
        });
        binding.tvSat.setOnClickListener(v -> {
            if("true".equals(binding.tvSat.getTag().toString())){
                binding.tvSat.setBackgroundResource(R.drawable.shape_oval_cccccc);
                binding.tvSat.setTag("false");
            }else{
                binding.tvSat.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
                binding.tvSat.setTag("true");
            }
        });

        //设置名字
        binding.goAlarmClock.setGoClick(() -> {
            CustomDialog.showAddText(mActivity, "输入闹钟名", (v, message) -> {
                binding.goAlarmClock.setRightText(message);
            });
        });

        //提交
        binding.tvSave.setOnClickListener(v -> {

            if(clockBeans != null){
                addEtData(String.valueOf(clockBeans.getAlarmId()));
            }else{
                getAlarmId();
            }

        });

        //是否编辑状态
        setEtText();
    }

    /*
    * 组装选择的时间数据
    * */
    private void getHour(){
        List<String> data = new ArrayList<>();
        List<String> seconds = new ArrayList<>();
        for (int i = 0; i < 24; i++)
        {
            data.add(i < 10 ? "0" + i : "" + i);
        }
        for (int i = 0; i < 60; i++)
        {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }

        binding.hourPick.setData(data);
        binding.secondPick.setData(seconds);
        binding.hourPick.setOnSelectListener(text -> {
            useHour = Integer.valueOf(text);
        });
        binding.secondPick.setOnSelectListener(text -> {
            useSecond = Integer.valueOf(text);
        });
    }

    /*
    * 是否时编辑状态
    * */
    private void setEtText(){
        if(clockBeans != null){
            binding.hourPick.setSelected(clockBeans.getHour());
            binding.secondPick.setSelected(clockBeans.getMinture());
            binding.goAlarmClock.setRightText(clockBeans.getAlarmName());
            binding.tvSave.setText("修改");

            mActivity.getMainTitle().setRightText("删除");
            mActivity.getMainTitle().getRightView().setOnClickListener(v -> {
                deletClock();
            });

            if(clockBeans.getMon() == 1){
                binding.tvMon.setTag("true");
                binding.tvMon.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvMon.setTag("false");
                binding.tvMon.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getTues() == 1){
                binding.tvTues.setTag("true");
                binding.tvTues.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvTues.setTag("false");
                binding.tvTues.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getWed() == 1){
                binding.tvWed.setTag("true");
                binding.tvWed.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvWed.setTag("false");
                binding.tvWed.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getThur() == 1){
                binding.tvThur.setTag("true");
                binding.tvThur.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvThur.setTag("false");
                binding.tvThur.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getFri() == 1){
                binding.tvFri.setTag("true");
                binding.tvFri.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvFri.setTag("false");
                binding.tvFri.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getSat() == 1){
                binding.tvSat.setTag("true");
                binding.tvSat.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvSat.setTag("false");
                binding.tvSat.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

            if(clockBeans.getSun() == 1){
                binding.tvSun.setTag("true");
                binding.tvSun.setBackgroundResource(R.drawable.shape_gradient_oval_ef8041_ef5b48);
            }else{
                binding.tvSun.setTag("false");
                binding.tvSun.setBackgroundResource(R.drawable.shape_oval_cccccc);
            }

        }
    }

    /*
    * 编辑或者添加闹钟
    * */
    private void addEtData(String alarmId){
        mActivity.showLoading();
        clearParams().setParams("openFlag","0").setParams("hour",String.valueOf(useHour))
                .setParams("minture",String.valueOf(useSecond))
                .setParams("mon",binding.tvMon.getTag().toString().equals("true") ? "1" : "0")
                .setParams("tues",binding.tvTues.getTag().toString().equals("true") ? "1" : "0")
                .setParams("wed",binding.tvWed.getTag().toString().equals("true") ? "1" : "0")
                .setParams("thur",binding.tvThur.getTag().toString().equals("true") ? "1" : "0")
                .setParams("fri",binding.tvFri.getTag().toString().equals("true") ? "1" : "0")
                .setParams("sat",binding.tvSat.getTag().toString().equals("true") ? "1" : "0")
                .setParams("sun",binding.tvSun.getTag().toString().equals("true") ? "1" : "0")
                .setParams("alarmName",binding.goAlarmClock.getRightText())
                .setParams("alarmId",alarmId);

        if(clockBeans != null){
            setParams("id",String.valueOf(clockBeans.getId()));
        }
        Controller.myRequest(ConstantsCode.ADD_ALARM,Constants.ADD_ALARM,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    /*
    * 获取alarmId
    * */
    private void getAlarmId(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_LAST_ID,Controller.TYPE_POST,getParams(), AlarmIdBeans.class,this);
    }

    /*
    * 删除闹钟
    * */
    private void deletClock(){
        mActivity.showLoading();
        clearParams().setParams("id",String.valueOf(clockBeans.getId()));
        Controller.myRequest(ConstantsCode.DEL_ALARM,Constants.DEL_ALARM,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof AlarmIdBeans){
            AlarmIdBeans idBeans = (AlarmIdBeans)data;
            addEtData(String.valueOf(idBeans.getData().getAlarmId()));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.ADD_ALARM){
            if(clockBeans != null){
                mActivity.showSuccess("修改成功！");
            }else{
                mActivity.showSuccess("添加成功！");
            }
        }
        if(code == ConstantsCode.DEL_ALARM){
            mActivity.showSuccess("删除成功！");
        }
    }
}
