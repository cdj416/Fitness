package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ProductReviewBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class ProductReviewAdapter extends BaseQuickAdapter<ProductReviewBeans.DataBean, BaseViewHolder> {

    //纯金额显示类型
    public static final int SHOW_MONEY = 0;
    //纯积分显示类型
    public static final int SHOW_POINT = 1;
    //金额加积分显示类型
    public static final int SHOW_MONEY_POINT = 2;

    public ProductReviewAdapter(){
        super(R.layout.item_neworders_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductReviewBeans.DataBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.goodName,item.getG_name())
                .setText(R.id.goodPrice, BaseUtil.getNoZoon(item.getGp_price()))
                .setText(R.id.goodNum,"x"+item.getBuy_num())
                .setText(R.id.goodsPoint,BaseUtil.getNoZoon(item.getGp_point()));

        if(BaseUtil.isValue(item.getSku_names())){
            helper.setVisible(R.id.goodType,true).setText(R.id.goodType,item.getSku_names());
        }else{
            helper.setVisible(R.id.goodType,false);
        }

        if(item.getShowType() == SHOW_MONEY){
            helper.getView(R.id.pointBox).setVisibility(View.GONE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }else if(item.getShowType() == SHOW_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            helper.getView(R.id.priceBox).setVisibility(View.GONE);
        }else if (item.getShowType() == SHOW_MONEY_POINT){
            helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.addMark).setVisibility(View.VISIBLE);
            helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
        }
    }
}
