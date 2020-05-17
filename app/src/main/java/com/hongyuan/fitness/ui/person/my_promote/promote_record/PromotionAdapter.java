package com.hongyuan.fitness.ui.person.my_promote.promote_record;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class PromotionAdapter extends BaseQuickAdapter<PromotionRecordBeans.DataBean.ListBean, BaseViewHolder> {

    public PromotionAdapter(){
        super(R.layout.item_promotion_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, PromotionRecordBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.superiorHead));

        helper.setText(R.id.superiorName,item.getM_name()).setText(R.id.superiorTel,item.getM_mobile());
    }
}
