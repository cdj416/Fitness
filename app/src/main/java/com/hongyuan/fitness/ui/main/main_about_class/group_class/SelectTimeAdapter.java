package com.hongyuan.fitness.ui.main.main_about_class.group_class;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SelectTimeAdapter extends BaseQuickAdapter<TimeSelectBean, BaseViewHolder> {

    public SelectTimeAdapter(){
        super(R.layout.item_time_select);
    }
    @Override
    protected void convert(BaseViewHolder helper, TimeSelectBean item) {
        if(item.isSelect()){
            helper.setBackgroundColor(R.id.timeText,0xFFEF5B48)
                    .setTextColor(R.id.timeText,0xFFFFFFFF)
                    .setText(R.id.timeText,item.getTime());
        }else{
            helper.setBackgroundColor(R.id.timeText,0xFFFFFFFF)
                    .setTextColor(R.id.timeText,0xFF333333)
                    .setText(R.id.timeText,item.getTime());
        }
        if("全天".equals(item.getStartTime())){
            helper.setText(R.id.timeText,"全天");
        }
        helper.addOnClickListener(R.id.timeText);
    }
}
