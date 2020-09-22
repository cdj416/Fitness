package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.FindUserBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SmParaiseDetailsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class FindUserAdapter extends BaseQuickAdapter<FindUserBeans.DataBean.ListBean, BaseViewHolder> {

    public FindUserAdapter(){
        super(R.layout.item_paraise_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindUserBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.nameText,item.getM_name())
                .setImageResource(R.id.sexImg,item.getMi_sex() == 1 ? R.mipmap.person_boby_mark_img : R.mipmap.person_girl_mark_img);

        helper.addOnClickListener(R.id.goNext);

    }
}
