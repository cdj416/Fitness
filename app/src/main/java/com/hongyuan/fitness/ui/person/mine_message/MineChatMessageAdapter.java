package com.hongyuan.fitness.ui.person.mine_message;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.huanxin.ChatDataBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class MineChatMessageAdapter extends BaseQuickAdapter<ChatDataBeans, BaseViewHolder> {

    public MineChatMessageAdapter(){
        super(R.layout.item_to_chat_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, ChatDataBeans item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getAvatar()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.userName,item.getNickname())
                .setText(R.id.meassageText,item.getMessage())
                .setText(R.id.meassageTime,item.getLastTime());

        if(item.isUnread()){
            helper.setVisible(R.id.newMeassageMark,true).setText(R.id.newMeassageMark,String.valueOf(item.getUnreadNum()));
        }else{
            helper.setVisible(R.id.newMeassageMark,false);
        }

        helper.addOnClickListener(R.id.jumpBox).addOnClickListener(R.id.delete);
    }
}
