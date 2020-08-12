package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sviewmodel.AftersaleOrderViwModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class AfterSaleAdapter extends BaseQuickAdapter<AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean, BaseViewHolder> {

    //退款状态 1已申请 2已取消 3已驳回 4重新申请 5商家同意退款 6买家发货退回 7卖家拒绝确认收货 8卖家确认收到货  9已完成

    //纯金额显示类型
    public static final int SHOW_MONEY = 0;
    //纯积分显示类型
    public static final int SHOW_POINT = 1;
    //金额加积分显示类型
    public static final int SHOW_MONEY_POINT = 2;

    //订单状态
    private int orderStatus = 0;

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public AfterSaleAdapter(){
        super(R.layout.item_aftersale_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.goodName,item.getG_name())
                .setText(R.id.goodPrice, BaseUtil.getNoZoon(item.getGp_price()))
                .setText(R.id.goodsPoint, BaseUtil.getNoZoon(item.getGp_point()))
                .setText(R.id.goodNum,"x"+item.getBuy_num());

        if(BaseUtil.isValue(item.getSku_names())){
            helper.setText(R.id.goodType,item.getSku_names()).setVisible(R.id.goodType,true);
        }else{
            helper.setVisible(R.id.goodType,false);
        }

        if(item.getShowType() == SHOW_MONEY){
            helper.getView(R.id.pointBox).setVisibility(View.GONE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }else if(item.getShowType() == SHOW_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.GONE);
        }else if (item.getShowType() == SHOW_MONEY_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.VISIBLE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }

        if(item.getRefund_state() == 1){
            helper.setText(R.id.goRefund,"已申请");
        }else if(item.getRefund_state() == 2){
            helper.setText(R.id.goRefund,"退款");
        }else if(item.getRefund_state() == 3){
            helper.setText(R.id.goRefund,"已驳回");
        }else if(item.getRefund_state() == 4){
            helper.setText(R.id.goRefund,"退款");
        }else if(item.getRefund_state() == 5){
            helper.setText(R.id.goRefund,"已同意");
        }else if(item.getRefund_state() == 6){
            helper.setText(R.id.goRefund,"退回货物");
        }else if(item.getRefund_state() == 7){
            helper.setText(R.id.goRefund,"商家拒绝");
        }else if(item.getRefund_state() == 8){
            helper.setText(R.id.goRefund,"商家确认收货");
        }else if(item.getRefund_state() == 9){
            helper.setText(R.id.goRefund,"已退款");
        }else{
            helper.setText(R.id.goRefund,"退款");
        }

        if(orderStatus == AftersaleOrderViwModel.STATU_DELIVERY || orderStatus == AftersaleOrderViwModel.STATU_SHIPPED
        || orderStatus == AftersaleOrderViwModel.STATU_PICKEDUP || orderStatus == AftersaleOrderViwModel.STATU_BE_EVALUATED
        || orderStatus == AftersaleOrderViwModel.STATU_COMPLETED){
            helper.getView(R.id.goRefund).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.goRefund).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.goRefund);

    }


}
