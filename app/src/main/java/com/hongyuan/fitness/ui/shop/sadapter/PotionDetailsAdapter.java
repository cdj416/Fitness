package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.PotionDetailsBeans;

public class PotionDetailsAdapter extends BaseQuickAdapter<PotionDetailsBeans.DataBean.ListBean, BaseViewHolder> {

    public PotionDetailsAdapter(){
        super(R.layout.item_potion_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, PotionDetailsBeans.DataBean.ListBean item) {
        helper.setText(R.id.title,item.getPl_reason())
                .setText(R.id.timeText,item.getPl_date());

        if("add".equals(item.getPl_type())){
            helper.setText(R.id.numberText,"+"+item.getPl_point_num());
        }else{
            helper.setText(R.id.numberText,"-"+item.getPl_point_num());
        }
    }
}
