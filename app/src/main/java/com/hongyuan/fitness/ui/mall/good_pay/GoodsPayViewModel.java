package com.hongyuan.fitness.ui.mall.good_pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.app.PayTask;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGoodsPayBinding;
import com.hongyuan.fitness.ui.about_class.class_failure.FailureActivity;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.about_class.privite_class.preservation_course.ReservationSuccessBeans;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.PayUtil;

import java.util.Map;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GoodsPayViewModel extends CustomViewModel {

    //支付成功
    private final int PAY_SUCCESS = 0x1;
    //支付成功
    private final int PAY_FAILURE = 0x2;
    private ActivityGoodsPayBinding binding;
    private PayDataBean payDataBean;

    private String payType = "wechatPay";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = new Bundle();
            switch (msg.what){
                case PAY_SUCCESS:
                    if(payDataBean.getReservationData() != null){
                        reservationCourse();
                    }else{
                        bundle.putString("titleName","支付结果");
                        bundle.putString("successText","支付成功！");
                        bundle.putString("buttonText","完成");
                        startActivity(SuccessClassActivity.class,bundle);
                    }
                    break;
                case PAY_FAILURE:
                    bundle.putString("titleName","支付结果");
                    bundle.putString("failureText","支付失败！");
                    startActivity(FailureActivity.class,bundle);
                    break;
            }
        }
    };

    public GoodsPayViewModel(CustomActivity mActivity, ActivityGoodsPayBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        payDataBean = (PayDataBean)getBundle().getSerializable("payDataBean");
        if(BaseUtil.isValue(payDataBean.getShowPrice()) && Double.valueOf(payDataBean.getShowPrice()) > 0){
            binding.showPrice.setText(BaseUtil.getNoZoon(payDataBean.getShowPrice()));
        }else{
            binding.priceBox.setVisibility(View.GONE);
            binding.wechatBox.setVisibility(View.GONE);
            binding.alipayBox.setVisibility(View.GONE);
            payType = "goldPay";
        }
        if(BaseUtil.isValue(payDataBean.getShowPoint()) && Double.valueOf(payDataBean.getShowPoint()) > 0){
            binding.showPoint.setText(BaseUtil.getNoZoon(payDataBean.getShowPoint()));
        }else{
            binding.pointBox.setVisibility(View.GONE);
        }
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    /*
     * 自动约课
     * */
    private void reservationCourse(){
        //请求约课
        clearParams().setParams("start_date",payDataBean.getReservationData().getStart_date())
                .setParams("end_date",payDataBean.getReservationData().getEnd_date())
                .setParams("jl_mid",String.valueOf(payDataBean.getReservationData().getJl_mid()))
                .setParams("num","1")
                .setParams("cp_id",String.valueOf(payDataBean.getReservationData().getCp_id()));
        Controller.myRequest(Constants.ADD_COURSE_PRIVITE_APPOINTMENT,Controller.TYPE_POST,getParams(), ReservationSuccessBeans.class,this);
    }

    //微信支付
    public BindingCommand wechatPay = new BindingCommand(() -> changeSelect("wechatPay"));
    //支付宝支付
    public BindingCommand alipayPay = new BindingCommand(() -> changeSelect("alipayPay"));

    //吊旗支付接口
    public BindingCommand pay = new BindingCommand(this::call);

    /*
     * 选中状态改变
     * */
    private void changeSelect(String payType){
        binding.goldPay.setImageResource(R.mipmap.pay_huise_no_select_img);
        binding.wechatPay.setImageResource(R.mipmap.pay_huise_no_select_img);
        binding.alipayPay.setImageResource(R.mipmap.pay_huise_no_select_img);
        if("goldPay".equals(payType)){
            binding.goldPay.setImageResource(R.mipmap.pay_chengse_select_img);
        }
        if("wechatPay".equals(payType)){
            binding.wechatPay.setImageResource(R.mipmap.pay_chengse_select_img);
        }
        if("alipayPay".equals(payType)){
            binding.alipayPay.setImageResource(R.mipmap.pay_chengse_select_img);
        }
        this.payType = payType;
    }

    /*
    * 支付宝支付
    * */

    private void getAlipayPay() {
        clearParams().setParams("o_id",payDataBean.getO_id());
        Controller.myRequest(Constants.UNIFIEDORDER,Controller.TYPE_POST,getParams(), AlipayBean.class,this);
    }

    /*
    * 微信支付
    * */
    private void getWechatPay(){
        clearParams().setParams("o_id",payDataBean.getO_id());
        Controller.myRequest(Constants.GETPREPAYORDER,Controller.TYPE_POST,getParams(), WecathPayBean.class,this);
    }
    /*
    * 积分支付
    * */
    private void getGoldPay(){
        clearParams().setParams("o_id",payDataBean.getO_id());
        Controller.myRequest(Constants.POINT_PAY,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBean){
            PointBean pointBean = (PointBean)data;
            binding.lavePoint.setText("剩余积分："+pointBean.getData().getPoint());
        }

        if(data instanceof AlipayBean){
            AlipayBean alipayBean = (AlipayBean)data;
            getAlipayData(alipayBean.getData().getPay());
        }
        if(data instanceof WecathPayBean){
            WecathPayBean wecathPayBean = (WecathPayBean)data;
            //去掉起微信支付
            PayUtil.WechatPay(mActivity,wecathPayBean.getData());
        }

        if(data instanceof ReservationSuccessBeans){
            Bundle bundle = new Bundle();
            bundle.putString("titleName","预约私教课");
            bundle.putString("successText","预约成功！");
            bundle.putString("buttonText","返回");
            startActivity(SuccessClassActivity.class,bundle);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        //金币支付成功
        if(code == 1){
            Bundle bundle = new Bundle();
            bundle.putString("titleName","商品购买");
            bundle.putString("successText","支付成功！");
            bundle.putString("buttonText","完成");
            startActivity(SuccessClassActivity.class,bundle);
        }
    }

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

    private void call() {
        if ("wechatPay".equals(payType)) {
            getWechatPay();
        }
        if ("alipayPay".equals(payType)) {
            getAlipayPay();
        }
        if("goldPay".equals(payType)){
            getGoldPay();
        }
    }
}
