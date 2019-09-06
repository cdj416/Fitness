package com.hongyuan.fitness.custom_view.filter_view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public abstract class SingleReclerAdapter<DATA> extends BaseQuickAdapter<DATA, BaseViewHolder> {

    public SingleReclerAdapter(){
        super(R.layout.item_filter_single_recyler);
    }

    @Override
    protected void convert(BaseViewHolder helper, DATA item) {
       helper.setText(R.id.content,provideText(item));
       if(isSelect(item)){
           helper.setTextColor(R.id.content,mContext.getResources().getColor(R.color.color_EF5B48));
       }else{
           helper.setTextColor(R.id.content,mContext.getResources().getColor(R.color.color_FF333333));
       }
       helper.addOnClickListener(R.id.content);
    }

    public abstract String provideText(DATA t);
    public abstract boolean isSelect(DATA t);

}
