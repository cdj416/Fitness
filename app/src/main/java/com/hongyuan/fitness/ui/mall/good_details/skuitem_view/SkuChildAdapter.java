package com.hongyuan.fitness.ui.mall.good_details.skuitem_view;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsBean;

public class SkuChildAdapter extends BaseQuickAdapter<GoodDetailsBean.DataBean.InfoBean.SkuBean.ItemBean, BaseViewHolder> {

    public SkuChildAdapter(){
        super(R.layout.item_sku_child);
    }
    @Override
    protected void convert(BaseViewHolder helper, GoodDetailsBean.DataBean.InfoBean.SkuBean.ItemBean item) {
        helper.setText(R.id.skuName,item.getSku_name());
        if(item.isSelect()){
            helper.setBackgroundRes(R.id.skuName,R.drawable.shape_radius6_ef5b48)
                    .setTextColor(R.id.skuName,0xFFFFFFFF);
        }else{
            helper.setBackgroundRes(R.id.skuName,R.drawable.shape_radius6_00000000_stroke_cccccc)
                    .setTextColor(R.id.skuName,0xFF777777);
        }
        helper.addOnClickListener(R.id.skuName);
    }
}
