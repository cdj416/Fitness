package com.hongyuan.fitness.ui.find.topic;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SelectTopicLeftAdapter extends BaseQuickAdapter<SlectTopicLeftBeans.DataBean.ListBean, BaseViewHolder> {

    public SelectTopicLeftAdapter(){
        super(R.layout.item_selecttopic_left);
    }
    @Override
    protected void convert(BaseViewHolder helper, SlectTopicLeftBeans.DataBean.ListBean item) {
        if(item.isSelect()){
            helper.setText(R.id.leftTopicName,item.getCategory_name())
                    .setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_FFFFFF));
        }else{
            helper.setText(R.id.leftTopicName,item.getCategory_name())
                    .setTextColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_FF333333))
                    .setBackgroundColor(R.id.leftTopicName,mContext.getResources().getColor(R.color.color_F5F6FB));
        }
        helper.addOnClickListener(R.id.leftTopicName);
    }

}
