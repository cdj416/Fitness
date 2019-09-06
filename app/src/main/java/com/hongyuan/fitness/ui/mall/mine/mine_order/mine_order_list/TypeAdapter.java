package com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class TypeAdapter extends BaseQuickAdapter<TypeBeans, BaseViewHolder> {

    public TypeAdapter(){
        super(R.layout.item_commom_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeBeans item) {
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius16_ef5b48).setTextColor(R.id.name,0xFFFFFFFF)
                    .setText(R.id.name,item.getShowText());
        }else{
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius16_ffffff_stroke_979797).setTextColor(R.id.name,0xFF5F5F5F)
                    .setText(R.id.name,item.getShowText());
        }

        helper.addOnClickListener(R.id.box);
    }
}
