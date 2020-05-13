package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class ShopMainGoodsAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public ShopMainGoodsAdapter(){
        super(R.layout.item_shopmain_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,getName(item))
                .setText(R.id.goodsPrice,getPrice(item));

        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.left1).setVisibility(View.VISIBLE);
            helper.getView(R.id.left2).setVisibility(View.GONE);
            helper.getView(R.id.right1).setVisibility(View.GONE);
        }else if(helper.getAdapterPosition() == (getData().size() - 1)){
            helper.getView(R.id.left1).setVisibility(View.GONE);
            helper.getView(R.id.left2).setVisibility(View.GONE);
            helper.getView(R.id.right1).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.left1).setVisibility(View.GONE);
            helper.getView(R.id.left2).setVisibility(View.GONE);
            helper.getView(R.id.right1).setVisibility(View.VISIBLE);
        }

        if(isStore()){
            helper.getView(R.id.goodsName).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.GONE);
            helper.getView(R.id.storeName).setVisibility(View.VISIBLE);
            helper.setText(R.id.storeName,getName(item));
        }

        helper.addOnClickListener(R.id.goDetail);
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
    * 是否为店铺
    * */
    public abstract boolean isStore();

}
