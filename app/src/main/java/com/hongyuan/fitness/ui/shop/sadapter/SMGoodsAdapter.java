package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class SMGoodsAdapter<T>extends BaseQuickAdapter<T, BaseViewHolder> {

    //纯金额显示类型
    public static final int SHOW_MONEY = 0;
    //纯积分显示类型
    public static final int SHOW_POINT = 1;
    //金额加积分显示类型
    public static final int SHOW_MONEY_POINT = 2;

    public SMGoodsAdapter(){
        super(R.layout.item_shop_main_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,getName(item))
                .setText(R.id.goodsPrice,getPrice(item))
                .setText(R.id.goodsPoint,getPoint(item));

        if(getShowType(item) == SHOW_MONEY){
            helper.getView(R.id.pointBox).setVisibility(View.GONE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }else if(getShowType(item) == SHOW_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.GONE);
        }else if (getShowType(item) == SHOW_MONEY_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.VISIBLE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }

        helper.addOnClickListener(R.id.jumpBox);
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
    * 商品积分
    * */
    public abstract String getPoint(T item);
    /*
    * 显示类型
    * */
    public abstract int getShowType(T item);
}
