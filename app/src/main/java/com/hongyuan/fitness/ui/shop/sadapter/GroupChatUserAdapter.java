package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.GroupChatUserBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class GroupChatUserAdapter extends BaseQuickAdapter<GroupChatUserBeans.DataBean, BaseViewHolder> {

    public GroupChatUserAdapter(){
        super(R.layout.item_group_chat_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupChatUserBeans.DataBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.name,item.getMi_realname());

        helper.addOnClickListener(R.id.goChat);

    }
}
