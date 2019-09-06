package com.hongyuan.fitness.ui.about_class.group_class.group_details;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class ApplyPersonNumAdapter extends BaseQuickAdapter<MissionDetailBean.DataBean.MemberOcsBean, BaseViewHolder> {

    public ApplyPersonNumAdapter(){
        super(R.layout.item_applyperson_num);
    }

    @Override
    protected void convert(BaseViewHolder helper, MissionDetailBean.DataBean.MemberOcsBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
    }
}
