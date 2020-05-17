package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.LogisticsBeans;
import com.hongyuan.fitness.util.TimeUtil;

public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBeans.DataBean.ListBean, BaseViewHolder> {

    public LogisticsAdapter(){
        super(R.layout.item_logistics);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsBeans.DataBean.ListBean item) {

        helper.setText(R.id.month, TimeUtil.formatDate(item.getTime(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatM_D))
                .setText(R.id.time,TimeUtil.formatDate(item.getTime(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));

        helper.setText(R.id.content,item.getContext());
    }
}
