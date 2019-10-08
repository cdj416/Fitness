package com.hongyuan.fitness.ui.main.main_find.featured;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class FindTopFriendsAdapter extends BaseQuickAdapter<FriendsBeans.DataBean.ListBean, BaseViewHolder> {

    public FindTopFriendsAdapter(){
        super(R.layout.item_feature_top_friends);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        if(helper.getPosition() == 0){
            helper.getView(R.id.firstView).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.firstView).setVisibility(View.GONE);
        }

    }
}
