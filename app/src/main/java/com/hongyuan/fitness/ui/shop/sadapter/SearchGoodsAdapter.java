package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class SearchGoodsAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public SearchGoodsAdapter(){
        super(R.layout.item_search_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {

    }
}
