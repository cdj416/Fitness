package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.about_class.privite_class.course_details.CourseDetailsBean;
import com.hongyuan.fitness.util.BaseUtil;

public class PayOrderDetailPriceAdapter extends BaseQuickAdapter<CourseDetailsBean.DataBean.PriceListBean, BaseViewHolder> {

    public PayOrderDetailPriceAdapter() {
        super(R.layout.item_privite_course_pay_details_price);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetailsBean.DataBean.PriceListBean item) {

        if(item.isSelect()){
            helper.setText(R.id.showPriceText,item.getMin_num()+"节起 ￥"+ BaseUtil.getNoZoon(item.getPrice())+"/节")
                    .setImageResource(R.id.status,R.mipmap.yuanquanquan_select_img);
        }else{
            helper.setText(R.id.showPriceText,item.getMin_num()+"节起 ￥"+ BaseUtil.getNoZoon(item.getPrice())+"/节")
                    .setImageResource(R.id.status,R.mipmap.yuanquanquan_no_select_img);
        }

        helper.addOnClickListener(R.id.jumpBox);
    }
}
