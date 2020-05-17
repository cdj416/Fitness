package com.hongyuan.fitness.ui.shop.sadapter;

import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SsCollectBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SstoreCollectAdapter extends BaseQuickAdapter<SsCollectBeans.DataBean.ListBean, BaseViewHolder> {

    public SstoreCollectAdapter(){
        super(R.layout.item_shop_store_collect);
    }

    @Override
    protected void convert(BaseViewHolder helper, SsCollectBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getInfo().getStore_logo()).apply(options).into((RoundedImageView)helper.getView(R.id.shopImg));

        helper.setText(R.id.shopName,item.getInfo().getStore_name());

        ((RatingBar)helper.getView(R.id.myRat)).setRating(Float.parseFloat(item.getInfo().getG_score()));

        if(item.getInfo().getGoods_list() != null && item.getInfo().getGoods_list().size() > 0){
            if(item.getInfo().getGoods_list().size() == 1){
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                helper.addOnClickListener(R.id.img1);
            }
            if(item.getInfo().getGoods_list().size() == 2){
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                helper.addOnClickListener(R.id.img1);
                helper.addOnClickListener(R.id.img2);
            }
            if(item.getInfo().getGoods_list().size() >= 3){
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                Glide.with(mContext).load(item.getInfo().getGoods_list().get(2).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img3));
                helper.addOnClickListener(R.id.img1);
                helper.addOnClickListener(R.id.img2);
                helper.addOnClickListener(R.id.img3);
            }
        }

        helper.addOnClickListener(R.id.goStore);
    }
}
