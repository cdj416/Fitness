package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.HabitGoddsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class SMGoodsAdapter<T>extends BaseQuickAdapter<T, BaseViewHolder> {

    public SMGoodsAdapter(){
        super(R.layout.item_shop_main_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,getName(item))
                .setText(R.id.goodsPrice,getPrice(item));

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
}
