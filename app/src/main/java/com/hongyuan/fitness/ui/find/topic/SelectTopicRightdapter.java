package com.hongyuan.fitness.ui.find.topic;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class SelectTopicRightdapter extends BaseQuickAdapter<SlectTopicRighttBeans.DataBean.ListBean, BaseViewHolder> {

    public SelectTopicRightdapter(){
        super(R.layout.item_topic_right);
    }
    @Override
    protected void convert(BaseViewHolder helper, SlectTopicRighttBeans.DataBean.ListBean item) {
        helper.setText(R.id.rightTopicName,item.getCategory_name())
                .setText(R.id.rightTopicDes,item.getCount()+"人参与了讨论");

        helper.addOnClickListener(R.id.box);
    }

}
