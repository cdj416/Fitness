package com.hongyuan.fitness.ui.about_class.check_payment_method;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCheckPaymentMethodBinding;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CheckPayMethodViewModel extends CustomViewModel {

    public static final int CARD_BUY = 0X1;
    private int orderType;
    private ActivityCheckPaymentMethodBinding binding;

    private String payType;

    //购买会员卡需要的参数
    private String card_id;

    public CheckPayMethodViewModel(CustomActivity mActivity, ActivityCheckPaymentMethodBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        orderType = getBundle().getInt("orderType",CARD_BUY);
        binding.showPrice.setText(getBundle().getString("showPrice","0"));
        if(orderType == CARD_BUY){
            card_id = getBundle().getString("card_id","0");
        }
    }

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
    * 会员卡添加购买订单
    * */
    private void addCardOrder(){
        clearParams().setParams("card_id",card_id);
        Controller.myRequest(Constants.ADD_CARD_ORDER,Controller.TYPE_POST,getParams(), OrderBean.class,this);
    }


    /*
    * 支付宝支付
    * */
    private void alipayPayOrder(String o_id){
        clearParams().setParams("o_id",o_id);
        Controller.myRequest(Constants.UNIFIEDORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    //金币支付
    public BindingCommand goldPay = new BindingCommand(() -> changeSelect("goldPay"));
    //微信支付
    public BindingCommand wechatPay = new BindingCommand(() -> changeSelect("wechatPay"));
    //支付宝支付
    public BindingCommand alipayPay = new BindingCommand(() -> changeSelect("alipayPay"));
    //添加订单
    public BindingCommand addOrder = new BindingCommand(() -> {
        if(orderType == CARD_BUY){
            addCardOrder();
        }
    });


    @Override
    public void onSuccess(Object data) {
        if(data instanceof OrderBean){
            OrderBean orderBean = (OrderBean)data;
            if("alipayPay".equals(payType)){
                alipayPayOrder(orderBean.getData().getO_id());
            }

        }
    }
}
