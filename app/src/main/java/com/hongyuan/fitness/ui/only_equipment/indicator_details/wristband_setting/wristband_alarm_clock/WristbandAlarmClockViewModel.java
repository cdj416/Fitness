package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWristbandAlarmClockBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_alarm_clock.add_alarm_clock.WristbandAddClockActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.yolanda.health.qnblesdk.bean.QNAlarm;
import com.yolanda.health.qnblesdk.bean.QNWeek;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.listener.QNResultCallback;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;
import java.util.List;

public class WristbandAlarmClockViewModel extends CustomViewModel {

    private ActivityWristbandAlarmClockBinding binding;

    private WristbandAlarmClockAdapter adapter;

    private List<AlarmClockBeans.DataBean.ListBean> mList;

    //蓝牙扫描类
    private QNBleApi mQNBleApi;
    //发送命令
    private QNBandManager bandManager;
    //与手表链接状态
    private WristbandStautsUtils stautsUtils;

    public WristbandAlarmClockViewModel(CustomActivity mActivity, ActivityWristbandAlarmClockBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);
        mQNBleApi = QNBleApi.getInstance(mActivity);
        bandManager = mQNBleApi.getBandManager();

        LinearLayoutManager taskManager = new LinearLayoutManager(mActivity);
        taskManager.setOrientation(RecyclerView.VERTICAL);
        binding.mRecycler.setLayoutManager(taskManager);
        adapter = new WristbandAlarmClockAdapter();
        binding.mRecycler.setAdapter(adapter);
        adapter.addFooterView(getFooterHeight(binding.mRecycler));
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if(view.getId() == R.id.box){
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",mList.get(position));
                startActivity(WristbandAddClockActivity.class,bundle);
            }
            if(view.getId() == R.id.switchBt){
                Switch aSwitch = (Switch) view;

                if(stautsUtils.isBand()){
                    onLoading("设置中...");
                    //去设置闹钟
                    setAlarmClock(aSwitch.isChecked(),mList.get(position));
                }else{
                    CustomDialog.showMessage(mActivity,"手表已断开，请尝试从新连接。");
                }

            }
        });
    }

    /*
    * 设置闹钟
    * */
    private void setAlarmClock(boolean isOpen,AlarmClockBeans.DataBean.ListBean alarmClock){
        QNAlarm qnAlarm = new QNAlarm();
        qnAlarm.setAlarmId(alarmClock.getAlarmId());
        qnAlarm.setHour(alarmClock.getHour());
        qnAlarm.setMinute(alarmClock.getMinture());
        qnAlarm.setOpenFlag(isOpen);

        QNWeek qnWeek = new QNWeek();
        qnWeek.setMon(alarmClock.getMon() == 1);
        qnWeek.setTues(alarmClock.getTues() == 1);
        qnWeek.setWed(alarmClock.getWed() == 1);
        qnWeek.setThur(alarmClock.getThur() == 1);
        qnWeek.setFri(alarmClock.getFri() == 1);
        qnWeek.setSat(alarmClock.getSat() == 1);
        qnWeek.setSun(alarmClock.getSun() == 1);
        qnAlarm.setQnWeek(qnWeek);

        bandManager.syncAlarm(qnAlarm, (code, msg) -> {
            if(code == CheckStatus.OK.getCode()){
                oppenFlg(String.valueOf(alarmClock.getId()),isOpen);
            }else{
                onError(-1,"设置失败，请重试！");
            }
        });
    }

    /*
     * 获取底部控件
     * */
    public View getFooterHeight(RecyclerView v){
        View convertView = LayoutInflater
                .from(mActivity)
                .inflate(R.layout.view_wristband_footer_alarmclock_, (ViewGroup) v.getParent(), false);
        RelativeLayout addBox = convertView.findViewById(R.id.addBox);
        addBox.setOnClickListener(v1 -> {
            startActivity(WristbandAddClockActivity.class,null);
        });
        return convertView;
    }

    /*
    * 开启关闭闹钟
    * */
    private void oppenFlg(String id,boolean isOpen){
        //mActivity.showLoading();
        clearParams().setParams("id",id).setParams("openFlag",isOpen ? "1" : "0");
        Controller.myRequest(ConstantsCode.UPDATE_FLAG,Constants.UPDATE_FLAG,Controller.TYPE_POST,getParams(), AlarmClockBeans.class,this);
    }

    /*
     * 添加手环配置到服务器
     * */
    private void updataSetting(String key,String value){
        //mActivity.showLoading();
        clearParams().setParams(key,value);
        Controller.myRequest(Constants.ADD_SH_CONFIG,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_ALARM_LIST,Controller.TYPE_POST,getParams(), AlarmClockBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof AlarmClockBeans){
            mList = ((AlarmClockBeans)data).getData().getList();

            adapter.setNewData(mList);

            if(mList.size() > 0){
                updataSetting("alarm_notice","1");
            }else{
                updataSetting("alarm_notice","0");
            }
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        if(code == ConstantsCode.UPDATE_FLAG){
            showSuccess("设置成功！");
        }
    }
}
