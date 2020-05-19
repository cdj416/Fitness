package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public abstract class MhsRecAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public MhsRecAdapter(){
        super(R.layout.item_m_hsrec);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        helper.setText(R.id.searchName,getName(item));
        helper.addOnClickListener(R.id.searchName);
    }

    /*
     * 获取显示值
     * */
    public abstract String getName(T item);
}
