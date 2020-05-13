package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ScouponsBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class SgoodsGouponAdapter extends BaseQuickAdapter<ScouponsBean.DataBean, BaseViewHolder> {

    public SgoodsGouponAdapter(){
        super(R.layout.item_dialog_goodsdetails_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScouponsBean.DataBean item) {
        helper.setText(R.id.money,BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.useText,"满"+ BaseUtil.getNoZoon(item.getMin_money())+"可使用")
                .setText(R.id.titleText,item.getCoupon_name())
                .setText(R.id.beText,item.getCoupon_note())
                .setText(R.id.validityPeriod,"有效期："+ TimeUtil.formatDate(item.getStart_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)
                +"-"+TimeUtil.formatDate(item.getStart_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD));

        helper.addOnClickListener(R.id.cReceive);
    }
}
