package com.hongyuan.fitness.ui.webview;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.ui.mall.good_pay.AlipayBean;
import com.hongyuan.fitness.ui.mall.good_pay.WecathPayBean;
import com.hongyuan.fitness.util.PayUtil;
import com.just.agentweb.AgentWeb;

import java.util.Map;

public class WebPayModelUtils extends CustomViewModel {

    //支付成功
    private final int PAY_SUCCESS = 0x1;
    //支付成功
    private final int PAY_FAILURE = 0x2;

    public static AgentWeb agent = null;

    public WebPayModelUtils(CustomActivity mActivity, AgentWeb agent) {
        super(mActivity);
        this.agent = agent;
    }

    /*
    * 微信支付结果处理
    * */
    public static void WXPayDealWith(){
        //回调js，让js处理跳转
        agent.getJsAccessEntrace().quickCallJs("gotoRouter");
    }

    /*
     * 支付宝支付
     * */
    public void alipayPay(String o_id) {
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(Constants.UNIFIEDORDER,Controller.TYPE_POST,getParams(), AlipayBean.class,this);
    }

    /*
    * 微信支付
    * */
    public void wxPay(String o_id){
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(Constants.GETPREPAYORDER,Controller.TYPE_POST,getParams(), WecathPayBean.class,this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //回调js，让js处理跳转
            agent.getJsAccessEntrace().quickCallJs("gotoRouter");
        }
    };

    /*
     * 吊起支付宝
     * */
    private void getAlipayData(String orderInfo ){
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(mActivity);
            Map<String,String> result = alipay.payV2(orderInfo,true);
            Message msg = new Message();
            if(result.get("resultStatus").equals("9000")){
                msg.what = PAY_SUCCESS;
            }else{
                msg.what = PAY_FAILURE;
            }
            mHandler.sendMessage(msg);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof WecathPayBean){
            WecathPayBean wecathPayBean = (WecathPayBean)data;
            mActivity.showLoading();
            //去掉起微信支付
            PayUtil.WechatPay(mActivity,wecathPayBean.getData());
        }

        if(data instanceof AlipayBean){
            AlipayBean alipayBean = (AlipayBean)data;
            getAlipayData(alipayBean.getData().getPay());
        }
    }
}
