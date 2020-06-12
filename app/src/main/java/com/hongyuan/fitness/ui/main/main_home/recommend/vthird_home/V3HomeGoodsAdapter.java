package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.V4HomeBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3HomeGoodsAdapter extends BaseQuickAdapter<V4HomeBeans.DataBean.GoodsBean, BaseViewHolder> {

    public V3HomeGoodsAdapter(){
        super(R.layout.item_home_goods);
    }
    @Override
    protected void convert(BaseViewHolder helper, V4HomeBeans.DataBean.GoodsBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

        helper.setText(R.id.goodName,item.getG_name());
        if(Double.valueOf(item.getG_price()) > 0){
            helper.getView(R.id.priceMark).setVisibility(View.VISIBLE);
            helper.setText(R.id.goodPrice,BaseUtil.getNoZoon(item.getG_price()));
        }else{
            helper.getView(R.id.priceMark).setVisibility(View.GONE);
        }

        if(Double.valueOf(item.getG_price()) > 0 && item.getG_point() > 0){
            helper.getView(R.id.addMark).setVisibility(View.VISIBLE);
            helper.getView(R.id.pointText).setVisibility(View.VISIBLE);
            helper.setText(R.id.goodPoint,BaseUtil.getNoZoon(item.getG_point()));
        }else{
            helper.getView(R.id.addMark).setVisibility(View.GONE);
            if(item.getG_point() > 0){
                helper.getView(R.id.pointText).setVisibility(View.VISIBLE);
                helper.getView(R.id.goodPoint).setVisibility(View.VISIBLE);
                helper.setText(R.id.goodPoint,BaseUtil.getNoZoon(item.getG_point()));
            }else{
                helper.getView(R.id.pointText).setVisibility(View.GONE);
                helper.getView(R.id.goodPoint).setVisibility(View.GONE);
            }
        }

        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.startWith).setVisibility(View.VISIBLE);
            helper.getView(R.id.normWhith).setVisibility(View.VISIBLE);
            helper.getView(R.id.endWith).setVisibility(View.GONE);
        }else if(helper.getAdapterPosition() > 0 && helper.getAdapterPosition() < (getData().size() - 1)){
            helper.getView(R.id.startWith).setVisibility(View.GONE);
            helper.getView(R.id.endWith).setVisibility(View.GONE);
            helper.getView(R.id.normWhith).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.startWith).setVisibility(View.GONE);
            helper.getView(R.id.endWith).setVisibility(View.VISIBLE);
            helper.getView(R.id.normWhith).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.jumpBox);
    }
}
