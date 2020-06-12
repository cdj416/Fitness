package com.hongyuan.fitness.ui.person.newedition.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.MineOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MemberCardOrdersAdapter extends BaseQuickAdapter<MineOrderBeans.DataBean.ListBean, BaseViewHolder> {

    public MemberCardOrdersAdapter(){
        super(R.layout.item_mine_order);
    }
    @Override
    protected void convert(BaseViewHolder helper, MineOrderBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getO_img()).apply(options).into((RoundedImageView)helper.getView(R.id.orderImg));

        helper.setText(R.id.orderTime,item.getAdd_date()).setText(R.id.orderStatus,getText(item.getO_pay_state()))
                .setTextColor(R.id.orderStatus,getTextColor(item.getO_pay_state()))
                .setText(R.id.orderName,item.getO_name()).setText(R.id.orderSku,getSkuStr(item.getSku()))
                .setText(R.id.orderNum,"x"+item.getO_num())
                .setText(R.id.orderNumText,"共"+item.getO_num()+"件商品");

        if(BaseUtil.isValue(item.getO_price()) && Double.valueOf(item.getO_price()) > 0){
            helper.getView(R.id.orderPrice).setVisibility(View.VISIBLE);
            helper.setText(R.id.orderPrice,BaseUtil.getNoZoon(item.getO_price()));
        }else{
            helper.getView(R.id.orderPrice).setVisibility(View.GONE);
        }
        if(BaseUtil.isValue(item.getO_money()) && Double.valueOf(item.getO_money()) > 0){
            helper.setText(R.id.orderAllPrice, BaseUtil.getNoZoon(item.getO_money()));
        }
        if(BaseUtil.isValue(item.getO_price()) && Double.valueOf(item.getOp_point()) > 0){
            helper.getView(R.id.orderPoint).setVisibility(View.VISIBLE);
            helper.getView(R.id.orderAllPoint).setVisibility(View.VISIBLE);
            helper.setText(R.id.orderPoint,BaseUtil.getNoZoon("积分："+item.getOp_point()));
            helper.setText(R.id.orderAllPoint,"总积分："+BaseUtil.getNoZoon(BigDecimalUtils.mul(String.valueOf(item.getOp_point()),String.valueOf(item.getO_num()),2)));
        }else{
            helper.getView(R.id.orderPoint).setVisibility(View.GONE);
            helper.getView(R.id.orderAllPoint).setVisibility(View.GONE);
        }
        if(item.getO_pay_state() == 2){
            helper.getView(R.id.operatingBox).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.operatingBox).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.jumpBox).addOnClickListener(R.id.cancelOrder)
                .addOnClickListener(R.id.goPay);

    }

    /*
    * 获取状态所对应的颜色值
    * */
    private int getTextColor(int type){
        int color = 0xFFEF5B48;
        switch (type){
            case 1:
                color = 0xFF23BB42;
                break;
            case 2:
                color = 0xFFEF5B48;
                break;
            case 5:
                break;
        }
        return color;
    }

    /*
    * 获取状态所对应的文字显示
    * */
    private String getText(int type){
        String text = "已支付";
        switch (type){
            case 1:
                text = "已支付";
                break;
            case 2:
                text = "待支付";
                break;
            case 5:
                text = "已取货";
                break;
        }
        return text;
    }

    /*
    * 获取规格的字符串显示
    * */
    private String getSkuStr(List<String> sku){
        if(sku == null || sku.size() <= 0){
            return "";
        }
        String showSku = "";
        for(String text:sku){
            showSku += " "+text;
        }
        return showSku;
    }
}
