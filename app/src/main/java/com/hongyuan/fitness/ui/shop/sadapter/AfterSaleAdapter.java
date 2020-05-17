package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class AfterSaleAdapter extends BaseQuickAdapter<AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean, BaseViewHolder> {

    public AfterSaleAdapter(){
        super(R.layout.item_aftersale_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.goodName,item.getG_name())
                .setText(R.id.goodPrice, BaseUtil.getNoZoon(item.getGp_price()))
                .setText(R.id.goodNum,"x"+item.getBuy_num());

        if(BaseUtil.isValue(item.getSku_names())){
            helper.setText(R.id.goodType,item.getSku_names()).setVisible(R.id.goodType,true);
        }else{
            helper.setVisible(R.id.goodType,false);
        }
    }
}
