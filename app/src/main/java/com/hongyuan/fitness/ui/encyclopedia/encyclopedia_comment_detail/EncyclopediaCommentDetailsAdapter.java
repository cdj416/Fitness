package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.encyclopedia.vthird_change.V3CommentDetailsBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class EncyclopediaCommentDetailsAdapter extends BaseQuickAdapter<V3CommentDetailsBeans.DataBean.ListBean, BaseViewHolder> {

    public EncyclopediaCommentDetailsAdapter(){
        super(R.layout.item_comment_details);
    }

    @Override
    protected void convert(final BaseViewHolder helper, V3CommentDetailsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.fName,item.getM_name()).setText(R.id.commentContent,item.getAr_content())
        .setText(R.id.attention,String.valueOf(item.getPraise_num())).setText(R.id.timeAfter, TimeUtil.friendly_time(item.getAdd_date()));



        if(item.getIs_praise() == 0){
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_huise_img);
        }else{
            ViewChangeUtil.changeLeftDrawable(mContext,helper.getView(R.id.attention),R.mipmap.like_chengse_img);
        }


        String showName;
        if(item.getAr_id_father() != item.getFirst_ar_id()){
            showName = "回复"+item.getTo_m_name()+":"+item.getAr_content();
            SpannableString spannableString = new SpannableString(showName);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#EF5B48")), 2,showName.indexOf(":"), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.commentContent, spannableString);
        }else{
            showName = item.getAr_content();
            helper.setText(R.id.commentContent, showName);
        }

        helper.addOnClickListener(R.id.outBox).addOnClickListener(R.id.attention);
    }

}
