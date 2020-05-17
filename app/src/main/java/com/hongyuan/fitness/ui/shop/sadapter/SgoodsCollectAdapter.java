package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SgCollectGoodsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SgoodsCollectAdapter extends BaseQuickAdapter<SgCollectGoodsBeans.DataBean.ListBean, BaseViewHolder> {

    public SgoodsCollectAdapter(){
        super(R.layout.item_shop_goods_collect);
    }

    @Override
    protected void convert(BaseViewHolder helper, SgCollectGoodsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getInfo().getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.shopName,item.getInfo().getG_name())
                .setText(R.id.price,item.getInfo().getG_price());

        helper.addOnClickListener(R.id.box);
    }
}
