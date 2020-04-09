package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import android.graphics.Paint;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class V4CardsListAdapter extends BaseQuickAdapter<V4CardsListBeans.DataBean.ListBean, BaseViewHolder> {

    public V4CardsListAdapter(){
        super(R.layout.item_v4cards_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, V4CardsListBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
        Glide.with(mContext).load(item.getC_img()).apply(options).into((RoundedImageView)helper.getView(R.id.cardImg));

        helper.setText(R.id.cardName,item.getCard_name())
                .setText(R.id.cardStore,item.getOs_names())
                .setText(R.id.oldPrice,"¥"+BaseUtil.getNoZoon(item.getCard_original_price()))
                .setText(R.id.cardDays,"有效期："+item.getCard_days()+"天")
                .setText(R.id.cardPrice, BaseUtil.getNoZoon(item.getCard_sale_price()));
        ((TextView)helper.getView(R.id.oldPrice)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.addOnClickListener(R.id.jumpBox);
    }
}
