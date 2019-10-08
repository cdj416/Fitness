package com.hongyuan.fitness.ui.person.daily_punch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class DailyPunchdapter extends BaseQuickAdapter<DailyPunchDataListBean.DataBean.ListBean, BaseViewHolder> {

    public DailyPunchdapter(){
        super(R.layout.item_daily_punch);
    }
    @Override
    protected void convert(BaseViewHolder helper, DailyPunchDataListBean.DataBean.ListBean item) {
        if(BaseUtil.isValue(item.getWeek())){
            helper.setText(R.id.dataText, TimeUtil.formatDate(item.getNow_date(),TimeUtil.dateFormatYMD,TimeUtil.dateFormatD));
            if(item.getIs_qd() == 1){
                helper.setBackgroundRes(R.id.dataText,R.drawable.shape_oval_7b7baa);
            }else{
                helper.setBackgroundRes(R.id.dataText,R.color.transparent);
            }
            if("-1".equals(item.getWeek())){
                helper.setTextColor(R.id.dataText,mContext.getResources().getColor(R.color.color_83839b));
            }else{
                helper.setTextColor(R.id.dataText,mContext.getResources().getColor(R.color.color_FFFFFF));
            }
        }else{
            helper.setText(R.id.dataText, "");
        }

    }
}
