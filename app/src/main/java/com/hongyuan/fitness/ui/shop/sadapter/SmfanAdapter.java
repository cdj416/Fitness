package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class SmfanAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public SmfanAdapter(){
        super(R.layout.item_message_fan);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.topHeight).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.topHeight).setVisibility(View.GONE);
        }
    }
}
