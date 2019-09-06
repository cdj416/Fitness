package com.hongyuan.fitness.ui.mall.mine.point_bill;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class PointBillAdapter extends BaseQuickAdapter<PointBillBean.DataBean.ListBean, BaseViewHolder> {

    public PointBillAdapter(){
        super(R.layout.item_point_bill);
    }
    @Override
    protected void convert(BaseViewHolder helper, PointBillBean.DataBean.ListBean item) {
        helper.setText(R.id.pointName,item.getPl_reason()).setText(R.id.pointTime,item.getPl_date());
        if(item.getPl_type().equals("reduce")){
            helper.setText(R.id.pointNum,"-"+item.getPl_point_num());
        }else{
            helper.setText(R.id.pointNum,"+"+item.getPl_point_num());
        }
    }
}
