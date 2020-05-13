package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.GoodsBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SsearchGoodsAdapter extends BaseQuickAdapter<GoodsBeans.DataBean.ListBean, BaseViewHolder> {

    public SsearchGoodsAdapter(){
        super(R.layout.item_search_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,item.getG_name())
                .setText(R.id.goodsPrice, BaseUtil.getNoZoon(item.getG_price()))
                .setText(R.id.saleNum,item.getG_sale_num()+"+已付款")
                .setText(R.id.evaluateNum,item.getG_evaluate_num()+"+评价");

        helper.addOnClickListener(R.id.goDetail);
    }
}
