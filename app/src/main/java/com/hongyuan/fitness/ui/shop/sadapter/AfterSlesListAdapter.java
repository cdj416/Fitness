package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.AfterSalesOrderListBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class AfterSlesListAdapter extends BaseQuickAdapter<AfterSalesOrderListBeans.DataBean.ListBean, BaseViewHolder> {

    //退款状态 1已申请 2已取消 3已驳回 4重新申请 5商家同意退款 6买家发货退回 7卖家拒绝确认收货 8卖家确认收到货  9已完成

    public AfterSlesListAdapter(){
        super(R.layout.item_aftersales);
    }

    @Override
    protected void convert(BaseViewHolder helper, AfterSalesOrderListBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));


        helper.setText(R.id.storeName,item.getStore_name())
                .setText(R.id.returnSta,getReturnStr1(item.getRefund_state()))
                .setText(R.id.goodName,item.getG_name())
                .setText(R.id.returnPrice, BaseUtil.getNoZoon(item.getRefund_money()))
                .setText(R.id.goodNum,"x"+item.getBuy_num())
                .setText(R.id.statuStr,getReturnStr1(item.getRefund_state())+" "+getReturnStr2(item.getRefund_state(),item.getIs_refund(),item.getRefund_money()));

        if(BaseUtil.isValue(item.getSku_names())){
            helper.setText(R.id.goodType,item.getSku_names()).setVisible(R.id.goodType,true);
        }else{
            helper.setVisible(R.id.goodType,false);
        }

        if(item.getRefund_state() == 9 || item.getRefund_state() == 4 || item.getRefund_state() == 3 || item.getRefund_state() == 2){
            helper.getView(R.id.cancelReturn).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.cancelReturn).setVisibility(View.VISIBLE);
        }

        if(item.getRefund_state() == 5){
            helper.getView(R.id.etLogtesg).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.etLogtesg).setVisibility(View.GONE);
        }

        if(item.getRefund_state() == 3 || item.getRefund_state() == 4 || item.getRefund_state() == 7){
            helper.getView(R.id.againApply).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.againApply).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.cancelReturn)
                .addOnClickListener(R.id.etLogtesg)
                .addOnClickListener(R.id.againApply)
                .addOnClickListener(R.id.updateApply)
                .addOnClickListener(R.id.lookDetail)
                .addOnClickListener(R.id.goDetail);
    }

    /*
    * 退款状态描述
    * */
    private String getReturnStr1(int status){
        if(status == 2){
            return "退款关闭";
        }
        if(status == 9){
            return "退款完成";
        }

        return "退款中";
    }

    /*
    * 退款状态二级描述
    * */
    private String getReturnStr2(int status,int returnType,String returnPrice){
        if(status == 1){
            return " 等待商家处理";
        }
        if(status == 2){
            return " 退款已关闭";
        }
        if(status == 3){
            return " 申请被驳回";
        }
        if(status == 4){
            return " 请重新申请退款";
        }
        if(status == 5 && returnType == 1){
            return " 请寄回退货商品";
        }
        if(status == 6){
            return " 等待商家收货";
        }
        if(status == 7){
            return " 卖家已拒绝";
        }
        if(status == 8){
            return " 等待商家处理退款";
        }
        if(status == 9){
            return " 退款成功¥"+returnPrice+"(原路返回)";
        }

        return "未知状态值";
    }
}
