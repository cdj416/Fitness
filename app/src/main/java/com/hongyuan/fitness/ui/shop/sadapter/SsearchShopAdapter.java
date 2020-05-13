package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.ShopsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SsearchShopAdapter extends BaseQuickAdapter<ShopsBeans.DataBean.ListBean, BaseViewHolder> {

    public SsearchShopAdapter(){
        super(R.layout.item_search_shops);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getStore_logo()).apply(options).into((RoundedImageView)helper.getView(R.id.shopImg));

        if(item.getGoods_list() != null && item.getGoods_list().size() > 0){
            helper.getView(R.id.goodsBox).setVisibility(View.VISIBLE);
            if(item.getGoods_list().size() >= 3){
                helper.setVisible(R.id.img1,true);
                helper.setVisible(R.id.img2,true);
                helper.setVisible(R.id.img3,true);
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                Glide.with(mContext).load(item.getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                Glide.with(mContext).load(item.getGoods_list().get(2).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img3));

                helper.addOnClickListener(R.id.img1).getView(R.id.img1).setClickable(true);
                helper.addOnClickListener(R.id.img2).getView(R.id.img2).setClickable(true);
                helper.addOnClickListener(R.id.img3).getView(R.id.img3).setClickable(true);
            }
            if(item.getGoods_list().size() == 2){
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                Glide.with(mContext).load(item.getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                helper.setVisible(R.id.img1,true);
                helper.setVisible(R.id.img2,true);
                helper.setVisible(R.id.img3,false);

                helper.addOnClickListener(R.id.img1).getView(R.id.img1).setClickable(true);
                helper.addOnClickListener(R.id.img2).getView(R.id.img2).setClickable(true);
                helper.addOnClickListener(R.id.img3).getView(R.id.img3).setClickable(false);
            }
            if(item.getGoods_list().size() == 1){
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                helper.setVisible(R.id.img1,true);
                helper.setVisible(R.id.img2,false);
                helper.setVisible(R.id.img3,false);

                helper.addOnClickListener(R.id.img1).getView(R.id.img1).setClickable(true);
                helper.addOnClickListener(R.id.img2).getView(R.id.img2).setClickable(false);
                helper.addOnClickListener(R.id.img3).getView(R.id.img3).setClickable(false);
            }
        }else{
            helper.getView(R.id.goodsBox).setVisibility(View.GONE);

            helper.addOnClickListener(R.id.img1).getView(R.id.img1).setClickable(false);
            helper.addOnClickListener(R.id.img2).getView(R.id.img2).setClickable(false);
            helper.addOnClickListener(R.id.img3).getView(R.id.img3).setClickable(false);
        }

        RatingBar ratingBar = helper.getView(R.id.myRat);
        ratingBar.setRating(Float.valueOf(item.getG_score()));
        helper.setText(R.id.shopName,item.getStore_name())
                .setText(R.id.collectionNum,item.getCollection_num()+"人收藏");

        helper.addOnClickListener(R.id.goStore);
    }
}
