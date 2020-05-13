package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SorderCouponBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class SorderCouponsAdapter extends BaseQuickAdapter<SorderCouponBeans.DataBean.ListBean, BaseViewHolder> {

    public SorderCouponsAdapter(){
        super(R.layout.item_sorder_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, SorderCouponBeans.DataBean.ListBean item) {
        helper.setText(R.id.couponMoney, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.couponUseMoney,"满"+BaseUtil.getNoZoon(item.getMin_money())+"使用")
                .setText(R.id.couponName,item.getCoupon_name())
                .setText(R.id.couponUseData,"有效期："+ TimeUtil.formatDate(item.getEnd_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD))
                .setText(R.id.note,item.getCoupon_note());

        if(item.isSelect()){
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius6_border_ef5b48);
        }else{
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radiu4_border_dddddd_soid_fffff);
        }

        helper.addOnClickListener(R.id.box);
    }
}
