package com.hongyuan.fitness.ui.shop.sadapter;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopLikesActivity;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class SMLikeGoodsAdapter<T>extends BaseQuickAdapter<T, BaseViewHolder> {

    public SMLikeGoodsAdapter(){
        super(R.layout.item_shop_like_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,getName(item))
                .setText(R.id.goodsPrice,getPrice(item));

        helper.addOnClickListener(R.id.jumpBox);

        helper.getView(R.id.openMc).setOnClickListener(v -> {
            helper.getView(R.id.mcBox).setVisibility(View.VISIBLE);
        });
        helper.getView(R.id.closeMc).setOnClickListener(v -> {
            helper.getView(R.id.mcBox).setVisibility(View.GONE);
        });
        helper.getView(R.id.noLike).setOnClickListener(v -> {
            helper.getView(R.id.mcBox).setVisibility(View.GONE);
        });
        helper.getView(R.id.goLikes).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("g_id",getGid(item));
            ((CustomActivity)mContext).startActivity(ShopLikesActivity.class,bundle);
        });
    }

    /*
     * 获取图片路径
     * */
    public abstract String getImg(T item);
    /*
     * 获取商品名
     * */
    public abstract String getName(T item);
    /*
     * 获取商品价格
     * */
    public abstract String getPrice(T item);
    /*
     * 获取商品id
     * */
    public abstract String getGid(T item);
}
