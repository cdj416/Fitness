package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.databinding.ActivityBottomAftersaleorderBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sactivity.LogisticsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ProductReviewActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.CancelOrderReasonBeans;
import com.hongyuan.fitness.ui.shop.sbeans.KeFuBeans;
import com.hongyuan.fitness.ui.shop.sviewmodel.AftersaleOrderViwModel;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class AftersaleOrderBottomViewModel extends CustomViewModel {

    private ActivityBottomAftersaleorderBinding binding;


    //数据源
    private AftersaleOrderBeans.DataBean.InfoBean infoBean;
    private UnitBeanUtils utils;
    private KeFuBeans.DataBean keFuBeans;
    //原因集
    private List<CancelOrderReasonBeans.DataBean.ListBean> rList;

    public AftersaleOrderBottomViewModel(CustomActivity mActivity, ActivityBottomAftersaleorderBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
        getReason();
    }

    @Override
    protected void initView() {

        binding.keFu.setOnClickListener(v -> {
            if(keFuBeans != null){
                CustomDialog.keFuWay(mActivity, (v1, message) -> {
                    if(v1.getId() == R.id.telNum){
                        CustomDialog.callTel(mActivity, keFuBeans.getInfo().getM_mobile(), new CustomDialog.DialogClick() {
                            @Override
                            public void dialogClick(View v) {
                                callTel(keFuBeans.getInfo().getM_mobile());
                            }
                        });
                    }

                    if(v1.getId() == R.id.goChat){
                        HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                                ,keFuBeans.getInfo().getM_name(),keFuBeans.getInfo().getMi_head());
                        Bundle bundle = new Bundle();
                        bundle.putString("title",keFuBeans.getInfo().getM_name());
                        bundle.putString("username",keFuBeans.getInfo().getM_mobile());
                        bundle.putString("lastMsgId",null);
                        startActivity(ChatPageActivity.class,bundle);
                    }
                });
            }
        });
    }

    //去评价
    public BindingCommand goEvaluation = new BindingCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("o_id",getBundle().getString("o_id"));
        startActivity(ProductReviewActivity.class,bundle);

        mActivity.finish();
    });
    //再次购买
    public BindingCommand goBuy = new BindingCommand(() -> {
        if(infoBean.getGoods_list().get(0).getG_id() != 0){
            Bundle bundle = new Bundle();
            bundle.putString("g_id",String.valueOf(infoBean.getGoods_list().get(0).getG_id()));
            startActivity(SgoodsDetailActivity.class,bundle);
        }else{
            CustomDialog.showMessage(mActivity,"商品已下架");
        }
    });
    //查看物流
    public BindingCommand goLookLogistics = new BindingCommand(() -> {
        Bundle bundle = new Bundle();
        bundle.putString("deliver_company_code",infoBean.getDeliver_info().getDeliver_company_code());
        bundle.putString("deliver_num",infoBean.getDeliver_info().getDeliver_num());
        startActivity(LogisticsActivity.class,bundle);
    });
    //确认收货
    public BindingCommand confirmReceipt = new BindingCommand(() -> {
        CustomDialog.promptDialog(mActivity, "是否确认收货？", "确定收货", "在想想", false, view -> {
            if(view.getId() == R.id.isOk){
                confirmOrder();
            }
        });
    });
    //去支付
    public BindingCommand goPay = new BindingCommand(() -> {
        PayDataBean payDataBean = new PayDataBean();
        payDataBean.setO_id(getBundle().getString("o_id"));
        payDataBean.setShowPoint("");
        payDataBean.setShowPrice(infoBean.getNeed_money());
        payDataBean.setLavePoint("");
        Bundle bundle = new Bundle();
        bundle.putSerializable("payDataBean",payDataBean);
        bundle.putSerializable("successBeans",getSuccessBeans());
        startActivity(GoodsPayActivity.class,bundle);
    });
    //取消订单
    public BindingCommand cancelOrdel = new BindingCommand(() -> {
        CustomDialog.scroller(mActivity, utils.getUnitList(rList), "订单取消", (view, message) -> {
            cancelOrder(getBundle().getString("o_id"),utils.getUseId(message));
        });
    });
    //提醒发货
    public BindingCommand dDelivery = new BindingCommand(() -> {
        CustomDialog.showMessage(mActivity,"已催促商家发货！");
    });
    //提醒发货
    public BindingCommand lateDelivery = new BindingCommand(() -> {
        CustomDialog.promptDialog(mActivity, "确定延长收货？每笔订单只能延长一次哦？", "确定延长", "再想想", false, v1 -> {

        });
    });


    /*
    * 设置详情数据
    * */
    public void setInfoData(AftersaleOrderBeans.DataBean.InfoBean infoBean){
        this.infoBean = infoBean;

        if(infoBean.getState() == AftersaleOrderViwModel.STATU_PAY){
            binding.waitPayBox.setVisibility(View.VISIBLE);
        }
        if(infoBean.getState() == AftersaleOrderViwModel.STATU_DELIVERY){

            binding.dDeliveryBox.setVisibility(View.INVISIBLE);
        }
        if(infoBean.getState() == AftersaleOrderViwModel.STATU_SHIPPED || infoBean.getState() == AftersaleOrderViwModel.STATU_PICKEDUP){
            if(infoBean.getDeliver_way() == 2){
                binding.selfMentionBox.setVisibility(View.VISIBLE);
            }else{
                binding.shippedBox.setVisibility(View.VISIBLE);
            }
        }
        if(infoBean.getState() == AftersaleOrderViwModel.STATU_BE_EVALUATED){
            if(infoBean.getDeliver_way() == 2){
                binding.ziTiEvaluatedBox.setVisibility(View.VISIBLE);
            }else{
                binding.beEvaluatedBox.setVisibility(View.VISIBLE);
            }
        }
        if(infoBean.getState() == AftersaleOrderViwModel.STATU_COMPLETED || infoBean.getState() == AftersaleOrderViwModel.STATU_ALL_COMPLETED){
            if(infoBean.getDeliver_way() == 2){
                binding.ziTiCompleteBox.setVisibility(View.VISIBLE);
            }else{
                binding.beEvaluatedBox.setVisibility(View.VISIBLE);
            }
        }

        //获取商家客服
        getKeFu(String.valueOf(infoBean.getStore().getStore_id()));
    }

    private void getKeFu(String store_id){
        clearParams().setParams("type","store").setParams("store_id",store_id);
        Controller.myRequest(Constants.GET_ONLINE_KF,Controller.TYPE_POST,getParams(), KeFuBeans.class,this);
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(){
        V3SuccessBeans beans = new V3SuccessBeans();

        beans.setTitleText("订单");
        beans.setShowText("购买成功");
        beans.setBtn2Text("完成");

        return beans;
    }

    //取消订单原因
    private void getReason(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_CANCEL_REASON_LIST,Controller.TYPE_POST,getParams(), CancelOrderReasonBeans.class,this);
    }

    //取消订单
    private void cancelOrder(String o_id,String reason_id){
        mActivity.showLoading();
        clearParams().setParams("o_id",o_id).setParams("reason_id",reason_id);
        Controller.myRequest(ConstantsCode.CANCLE_ORDER, Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    //确认订单
    private void confirmOrder(){
        mActivity.showLoading();
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(ConstantsCode.CONFIRM_ORDER,Constants.CONFIRM_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof CancelOrderReasonBeans){
            rList = ((CancelOrderReasonBeans)data).getData().getList();

            utils = new UnitBeanUtils<CancelOrderReasonBeans.DataBean.ListBean>() {
                @Override
                public String unit(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return String.valueOf(o.getReason_id());
                }

                @Override
                public String unit_cn(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return o.getName();
                }
            };
        }

        if(data instanceof KeFuBeans){
            keFuBeans = ((KeFuBeans)data).getData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.CANCLE_ORDER){
            mActivity.showSuccess("订单已取消");
        }

        if(code == ConstantsCode.CONFIRM_ORDER){
            mActivity.showSuccess("已确认收货");
        }
    }
}
