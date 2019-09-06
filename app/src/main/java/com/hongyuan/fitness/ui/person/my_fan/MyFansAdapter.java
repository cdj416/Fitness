package com.hongyuan.fitness.ui.person.my_fan;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_find.featured.FriendsBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyFansAdapter extends BaseQuickAdapter<FriendsBeans.DataBean.ListBean, BaseViewHolder> {

    public MyFansAdapter(){
        super(R.layout.item_friends);
    }
    @Override
    protected void convert(BaseViewHolder helper, FriendsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.name,item.getM_name()).setText(R.id.mark,"健身达人");
        if(item.getIs_xh() == 1){
            helper.setText(R.id.status,"互相关注").setBackgroundRes(R.id.status,R.drawable.shape_radius24_cccccc);
            helper.getView(R.id.status).setTag(false);
        }else{
            helper.setText(R.id.status,"关注Ta").setBackgroundRes(R.id.status,R.drawable.shape_radius16_ef5b48);
            helper.getView(R.id.status).setTag(true);
        }
        helper.addOnClickListener(R.id.status);

    }
}
