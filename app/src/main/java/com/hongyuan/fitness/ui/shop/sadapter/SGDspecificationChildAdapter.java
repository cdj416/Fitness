package com.hongyuan.fitness.ui.shop.sadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;

public class SGDspecificationChildAdapter extends BaseQuickAdapter<SgoodsDetailBeans.DataBean.InfoBean.SkuBean.ItemBean, BaseViewHolder> {

    public SGDspecificationChildAdapter(){
        super(R.layout.item_sgdetails_child_specification);
    }

    @Override
    protected void convert(BaseViewHolder helper, SgoodsDetailBeans.DataBean.InfoBean.SkuBean.ItemBean item) {
        helper.setText(R.id.specText,item.getSku_name());

        if(item.isSelect()){
            helper.setBackgroundRes(R.id.clickBox,R.drawable.shape_soid_ffe4e1_border_5b48)
                    .setTextColor(R.id.specText,mContext.getResources().getColor(R.color.color_EF5B48));
        }else{
            helper.setBackgroundRes(R.id.clickBox,R.drawable.shape_soid_ffffff_border_eeeeee)
                    .setTextColor(R.id.specText,mContext.getResources().getColor(R.color.color_FF333333));
        }

        helper.addOnClickListener(R.id.clickBox);
    }
}
