package com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.wristband_msg;

import android.util.Log;
import android.view.View;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWristbandMsgRemindBinding;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_fragments.WristbandStautsUtils;
import com.hongyuan.fitness.ui.only_equipment.indicator_details.wristband_setting.WristbandSettingStatus;
import com.hongyuan.fitness.util.CustomDialog;
import com.yolanda.health.qnblesdk.bean.QNBandMetrics;
import com.yolanda.health.qnblesdk.bean.QNRemindMsg;
import com.yolanda.health.qnblesdk.constant.CheckStatus;
import com.yolanda.health.qnblesdk.constant.QNBandConst;
import com.yolanda.health.qnblesdk.listener.QNResultCallback;
import com.yolanda.health.qnblesdk.out.QNBandManager;
import com.yolanda.health.qnblesdk.out.QNBleApi;

public class WristbandMsgViwModel extends CustomViewModel {

    private ActivityWristbandMsgRemindBinding binding;

    //蓝牙扫描类
    private QNBleApi mQNBleApi;
    //发送命令
    private QNBandManager bandManager;
    //与手表链接状态
    private WristbandStautsUtils stautsUtils;

    //消息设置状态
    private int msg_notice;
    private int wx_notice;
    private int qq_notice;

    public WristbandMsgViwModel(CustomActivity mActivity, ActivityWristbandMsgRemindBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        stautsUtils = WristbandStautsUtils.getInstance(mActivity);
        mQNBleApi = QNBleApi.getInstance(mActivity);
        bandManager = mQNBleApi.getBandManager();

        msg_notice = getBundle().getInt("msg_notice");
        wx_notice = getBundle().getInt("wx_notice");
        qq_notice = getBundle().getInt("qq_notice");

        if(msg_notice == 1){
            binding.msgSwitch.setChecked(true);
        }
        if(wx_notice == 1){
            binding.weChatSwitch.setChecked(true);
        }
        if(qq_notice == 1){
            binding.qqSwitch.setChecked(true);
        }

        binding.msgSwitch.setOnClickListener(v -> {
            if(binding.msgSwitch.isChecked()){
                setMsg(QNBandConst.NOTIFY_TYPE_MSG,"消息","信息","信息通知设置成功！");
                updataSetting("msg_notice","1");
            }else{
                updataSetting("msg_notice","0");
            }

        });
        binding.weChatSwitch.setOnClickListener(v -> {
            if(binding.weChatSwitch.isChecked()){
                setMsg(QNBandConst.NOTIFY_TYPE_WX,"消息","微信","微信消息设置成功！");
                updataSetting("wx_notice","1");
            }else{
                updataSetting("wx_notice","0");
            }
        });
        binding.qqSwitch.setOnClickListener(v -> {
            if(binding.qqSwitch.isChecked()){
                setMsg(QNBandConst.NOTIFY_TYPE_QQ,"消息","QQ","QQ消息设置成功！");
                updataSetting("qq_notice","1");
            }else{
                updataSetting("qq_notice","0");
            }
        });
    }

    /*
    *消息提醒
    * */
    private void setMsg(int type,String title,String soure,String content){
        if(!stautsUtils.isBand()){
            CustomDialog.showMessage(mActivity,"设备已断开，请先连接设备！");
            binding.msgSwitch.setChecked(false);
            binding.weChatSwitch.setChecked(false);
            binding.qqSwitch.setChecked(false);
            return;
        }
        QNRemindMsg msg = new QNRemindMsg();
        msg.setType(type);
        msg.setTitle(title);
        msg.setSource(soure);
        msg.setContent(content);

        bandManager.msgRemind(msg, (code, msg1) -> Log.e("cdj","========code===="+code+"======"+ msg1));
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
    public void onSuccess(Object data) {

    }
}
