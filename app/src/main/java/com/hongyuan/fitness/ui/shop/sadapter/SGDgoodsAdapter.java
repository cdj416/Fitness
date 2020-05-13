package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDeailStoreBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SGDgoodsAdapter extends BaseQuickAdapter<SgoodsDeailStoreBeans.DataBean.TjGoodsListBean, BaseViewHolder> {

    public SGDgoodsAdapter(){
        super(R.layout.item_sgd_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, SgoodsDeailStoreBeans.DataBean.TjGoodsListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.goodsName,item.getG_name()).setText(R.id.goodsPrice,item.getG_price());
    }
}
