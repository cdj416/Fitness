package com.hongyuan.fitness.ui.mall.home_goods;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class HomeGoodsAdapter extends BaseQuickAdapter<HomeGoodsBeans.DataBean.ListBean, BaseViewHolder> {

    public HomeGoodsAdapter(){
        super(R.layout.item_dialog_receive_goods);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeGoodsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getG_img()).apply(options).into((ImageView) helper.getView(R.id.goodsImg));

        helper.setText(R.id.goodsName,item.getG_name());

        if(item.isReceive()){
            helper.getView(R.id.gReceive).setClickable(false);
            helper.setText(R.id.gReceive,"已领取")
                    .setBackgroundRes(R.id.gReceive,R.drawable.shape_radius16_f1f1f8)
                    .setTextColor(R.id.gReceive,mContext.getResources().getColor(R.color.color_777777));
        }else{
            helper.getView(R.id.gReceive).setClickable(true);
            helper.setText(R.id.gReceive,"免费领取")
                    .setBackgroundRes(R.id.gReceive,R.drawable.shape_radius16_ef5b48)
                    .setTextColor(R.id.gReceive,mContext.getResources().getColor(R.color.color_FFFFFF));
            helper.addOnClickListener(R.id.gReceive);
        }
    }
}
