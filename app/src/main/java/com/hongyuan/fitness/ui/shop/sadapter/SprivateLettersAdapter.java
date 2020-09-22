package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SmPrivateLettersBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SprivateLettersAdapter extends BaseQuickAdapter<SmPrivateLettersBeans.DataBean, BaseViewHolder> {

    public SprivateLettersAdapter(){
        super(R.layout.item_message_private_letters);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmPrivateLettersBeans.DataBean item) {
        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.topHeight).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.topHeight).setVisibility(View.GONE);
        }

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMsg_category_img()).apply(options).into((RoundedImageView)helper.getView(R.id.messageImg));

        helper.setText(R.id.messageName,item.getTitle())
                .setText(R.id.messageDes,item.getMsg_content())
                .setText(R.id.msgTime, TimeUtil.formatDate(item.getMsg_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatXieMDHM));

        if(item.getCount() > 0){
            helper.setVisible(R.id.newMeassageMark,true).setText(R.id.newMeassageMark,String.valueOf(item.getCount()));
        }else{
            helper.setVisible(R.id.newMeassageMark,false);
        }

        helper.addOnClickListener(R.id.goDetail);
    }
}
