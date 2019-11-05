package com.hongyuan.fitness.ui.person.mine_message;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MineMessageAdapter extends BaseQuickAdapter<MineMessageBeans.DataBean, BaseViewHolder> {

    public MineMessageAdapter(){
        super(R.layout.item_mine_message);
    }
    @Override
    protected void convert(BaseViewHolder helper, MineMessageBeans.DataBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getMsg_category_img()).apply(options).into((RoundedImageView)helper.getView(R.id.messageImg));

        helper.setText(R.id.messageName,item.getMsg_category_name());
        helper.addOnClickListener(R.id.jumpBox);
    }
}
