package com.hongyuan.fitness.ui.promt_success;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class V3SuccessAdapter extends BaseQuickAdapter<V3SuccessBeans.ItemConten, BaseViewHolder> {

    public V3SuccessAdapter(){
        super(R.layout.item_v3_success);
    }
    @Override
    protected void convert(BaseViewHolder helper, V3SuccessBeans.ItemConten item) {
        helper.setText(R.id.itemTitle,item.getItemTitle())
                .setText(R.id.content, item.getContent());


        helper.addOnClickListener(R.id.cardBox);
    }
}
