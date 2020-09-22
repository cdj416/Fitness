package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SmParaiseDetailsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SmParaiseDetailsAdapter extends BaseQuickAdapter<SmParaiseDetailsBeans.DataBean.ListBean, BaseViewHolder> {

    public SmParaiseDetailsAdapter(){
        super(R.layout.item_paraise_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmParaiseDetailsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.nameText,item.getMi_realname())
                .setImageResource(R.id.sexImg,item.getMi_sex() == 1 ? R.mipmap.person_boby_mark_img : R.mipmap.person_girl_mark_img);

    }
}
