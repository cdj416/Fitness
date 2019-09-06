package com.hongyuan.fitness.ui.store;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
public class MarkTextAdapter extends BaseQuickAdapter<StoreDetailBean.DataBean.OsfBean, BaseViewHolder> {

    public MarkTextAdapter(){
        super(R.layout.item_mark_text);
    }
    @Override
    protected void convert(BaseViewHolder helper, StoreDetailBean.DataBean.OsfBean item) {
        helper.setText(R.id.showName,item.getOsf_name());
    }
}
