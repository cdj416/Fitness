package com.hongyuan.fitness.ui.about_class.privite_class.pay_order_detail.select_time;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class TabTimeAdapter extends BaseQuickAdapter<TabTimeBean.DataBean, BaseViewHolder> {

    public TabTimeAdapter(){
        super(R.layout.item_select_first_time_tab);
    }
    @Override
    protected void convert(BaseViewHolder helper, TabTimeBean.DataBean item) {
        TextView weekText = helper.getView(R.id.weekText);
        TextView dataText = helper.getView(R.id.dataText);
        helper.setText(R.id.weekText,item.getWeek());
        if(item.getIs_cur_date() == 1){
            dataText.setText("今日");
        }else{
            dataText.setText(item.getShowName());
        }

        if(item.isSelect()){
            weekText.setTextColor(0xFFEF5B48);
            dataText.setTextColor(0xFFEF5B48);
        }else{
            weekText.setTextColor(0xFF999999);
            dataText.setTextColor(0xFF999999);
        }

        helper.addOnClickListener(R.id.tabBox);

    }
}
