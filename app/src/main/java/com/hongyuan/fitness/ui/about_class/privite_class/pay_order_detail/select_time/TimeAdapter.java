package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class TimeAdapter extends BaseQuickAdapter<TimeBean.DataBean, BaseViewHolder> {

    public TimeAdapter(){
        super(R.layout.item_select_time_content);
    }
    @Override
    protected void convert(BaseViewHolder helper, TimeBean.DataBean item) {
        if(item.getIs_kong() == 1){
            helper.addOnClickListener(R.id.timeBox);
        }

        helper.setText(R.id.time,item.getNow_time().substring(0,5));
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_optional_selected);
            helper.setTextColor(R.id.isOk,0xFFFFFFFF);
            helper.setTextColor(R.id.time,0xFFFFFFFF);
            helper.setText(R.id.isOk,"可约");
        }else if(item.getIs_kong() == 1){
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_optional_default);
            helper.setTextColor(R.id.isOk,0xFF6ABE24);
            helper.setTextColor(R.id.time,0xFF333333);
            helper.setText(R.id.isOk,"可约");
            helper.getView(R.id.timeBox).setClickable(true);
        }else{
            helper.setBackgroundRes(R.id.timeBox,R.drawable.shape_time_not_optional);
            helper.setTextColor(R.id.isOk,0xFF999999);
            helper.setTextColor(R.id.time,0xFF999999);
            helper.setText(R.id.isOk,"不可约");
            helper.getView(R.id.timeBox).setClickable(false);
        }
    }
}
