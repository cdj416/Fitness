package com.hongyuan.fitness.ui.mall.good_pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.alipay.sdk.app.PayTask;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGoodsPayBinding;
import com.hongyuan.fitness.ui.about_class.privite_class.preservation_course.ReservationSuccessBeans;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.GroupCourseOrdersActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.MemberCardOrdersActivity;
import com.hongyuan.fitness.ui.person.newedition.activity.PriviteCourseOrdersActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sactivity.ShopNewOrderAcitivity;
import com.hongyuan.fitness.ui.shop.sbeans.PointBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.PayUtil;
import com.hongyuan.fitness.wxapi.WXPayEntryActivity;
import java.util.Map;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GoodsPayViewModel extends CustomViewModel {

    //支付成功
    private final int PAY_SUCCESS = 0x1;
    //支付成功
    private final int PAY_FAILURE = 0x2;
    private ActivityGoodsPayBinding binding;
    private PayDataBean payDataBean;
    public static V3SuccessBeans successBeans;

    private String payType = "wechatPay";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PAY_SUCCESS:
                    goSuccess();
                    break;
                case PAY_FAILURE:
                    goNext();
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
        successBeans = (V3SuccessBeans)getBundle().getSerializable("successBeans");

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

        binding.cancelPay.setOnClickListener(v -> CustomDialog.promptDialog(mActivity, "确定要取消支付吗？", "再想想", "确定", false, v1 -> {
            if(v1.getId() == R.id.isCannel){
                /*if(getBundle().getBoolean("isShop")){
                    mActivity.startActivity(ShopNewOrderAcitivity.class,null);
                    mActivity.finish();
                }else{
                    mActivity.finish();
                }*/

                goNext();
            }
        }));
        binding.pay.setOnClickListener(v -> call());
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
        super.onSuccess(data);

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
            mActivity.showLoading();
            //微信跳转要设置下
            WXPayEntryActivity.successBeans = successBeans;

            //去掉起微信支付
            PayUtil.WechatPay(mActivity,wecathPayBean.getData());
            mActivity.closeLoading();
        }

        if(data instanceof ReservationSuccessBeans){
            V3SuccessBeans beans = new V3SuccessBeans();
            beans.setType(V3SuccessBeans.TYPE.PRIVITECLASS);
            beans.setTitleText("预约私教课");
            beans.setShowText("预约成功");
            beans.setBtn2Text("返回");

            Bundle bundle = new Bundle();
            bundle.putSerializable("successBeans",beans);
            startActivity(V3SuccessActivity.class,bundle);
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        super.onSuccess(code,data);

        //金币支付成功
        if(code == 1){

            V3SuccessBeans beans = new V3SuccessBeans();
            beans.setType(V3SuccessBeans.TYPE.BUYGOODS);
            beans.setTitleText("商品购买");
            beans.setShowText("支付成功");
            beans.setBtn2Text("完成");

            Bundle bundle = new Bundle();
            bundle.putSerializable("successBeans",beans);
            startActivity(V3SuccessActivity.class,bundle);

        }else{
            //支付失败跳转
            goNext();
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

    /*
    * 三版跳转
    * */
    private void goSuccess(){
        if(BaseUtil.isValue(successBeans)){
            Bundle bundle = new Bundle();
            bundle.putSerializable("successBeans",successBeans);
            startActivity(V3SuccessActivity.class,bundle);
            mActivity.finish();
        }else{
            V3SuccessBeans beans = new V3SuccessBeans();
            beans.setType(V3SuccessBeans.TYPE.BUYGOODS);
            beans.setTitleText("支付结果");
            beans.setShowText("支付成功");
            beans.setBtn2Text("完成");

            Bundle bundle = new Bundle();
            bundle.putSerializable("successBeans",beans);
            startActivity(V3SuccessActivity.class,bundle);
        }

    }

    /*
     * 跳转处理
     * */
    private void goNext(){
        switch (successBeans.getType()){
            case BUYCARD:
                mActivity.startActivity(MemberCardOrdersActivity.class,null);
                break;
            case BUYGOODS:
                mActivity.startActivity(ShopNewOrderAcitivity.class,null);
                break;
            case GROUPCLASS:
                mActivity.startActivity(GroupCourseOrdersActivity.class,null);
                break;
            case PRIVITECLASS:
                mActivity.startActivity(PriviteCourseOrdersActivity.class,null);
                break;
            default:
                startActivity(MainActivity.class,null);
                break;
        }
    }
}
