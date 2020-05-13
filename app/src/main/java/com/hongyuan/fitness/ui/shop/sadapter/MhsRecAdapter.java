package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class MhsRecAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MhsRecAdapter(){
        super(R.layout.item_m_hsrec);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.searchName,item);
        helper.addOnClickListener(R.id.searchName);
    }
}
