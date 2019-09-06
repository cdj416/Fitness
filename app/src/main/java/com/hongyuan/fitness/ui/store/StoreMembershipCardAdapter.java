package com.hongyuan.fitness.ui.store;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreMembershipCardAdapter extends BaseQuickAdapter<CardItemBean.DataBean.ListBean, BaseViewHolder> {

    public StoreMembershipCardAdapter(){
        super(R.layout.item_store_membership_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, CardItemBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCard_img()).apply(options).into((RoundedImageView)helper.getView(R.id.cardBg));

        helper.setText(R.id.storeName,item.getOs_name()).setText(R.id.cardPrice, BaseUtil.getNoZoon(item.getCard_sale_price()));

        helper.addOnClickListener(R.id.cardBox);
    }
}
