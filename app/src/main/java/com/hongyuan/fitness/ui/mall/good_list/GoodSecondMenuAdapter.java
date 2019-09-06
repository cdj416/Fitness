package com.hongyuan.fitness.ui.mall.good_list;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class GoodSecondMenuAdapter extends BaseQuickAdapter<GoodListMeanBean.DataBean.ListBean, BaseViewHolder> {

    public GoodSecondMenuAdapter(){
        super(R.layout.item_commom_menu);
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodListMeanBean.DataBean.ListBean item) {
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius16_ef5b48).setTextColor(R.id.name,0xFFFFFFFF)
            .setText(R.id.name,item.getCategory_name());
        }else{
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius16_ffffff_stroke_979797).setTextColor(R.id.name,0xFF5F5F5F)
                    .setText(R.id.name,item.getCategory_name());
        }

        helper.addOnClickListener(R.id.box);
    }
}
