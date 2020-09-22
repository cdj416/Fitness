package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.MsgDetailsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class MsgDetailsAdapter extends BaseQuickAdapter<MsgDetailsBeans.DataBean.ListBean, BaseViewHolder> {

    private int msg_category_id;
    public MsgDetailsAdapter(int msg_category_id){
        super(R.layout.item_newmsg_details);
        this.msg_category_id = msg_category_id;
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgDetailsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMsg_category_img()).apply(options).into((RoundedImageView)helper.getView(R.id.msgImg));

        helper.setText(R.id.msgContent,item.getMsg_content()).setText(R.id.timeText,item.getMsg_date());

        if(msg_category_id == 1 || msg_category_id == 6 || msg_category_id == 5){
            if(item.getHref_id() != 0){
                helper.getView(R.id.goNext).setVisibility(View.VISIBLE);
            }else{
                helper.getView(R.id.goNext).setVisibility(View.GONE);
            }
        }else{
            helper.getView(R.id.goNext).setVisibility(View.VISIBLE);
        }

        helper.addOnClickListener(R.id.goNext);
    }
}
