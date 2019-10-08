package com.hongyuan.fitness.ui.person.push_share;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class PushShareAdapter extends BaseQuickAdapter<PushShareBeans.DataBean.ImgsBean, BaseViewHolder> {

    public PushShareAdapter(){
        super(R.layout.item_push_share);
    }

    @Override
    protected void convert(BaseViewHolder helper, PushShareBeans.DataBean.ImgsBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getImgUrl()).apply(options).into((RoundedImageView)helper.getView(R.id.shareImg));

        if(item.isSelect()){
            helper.setBackgroundRes(R.id.imgBox,R.drawable.shape_radius6_border_ef5b48);
        }else{
            helper.setBackgroundRes(R.id.imgBox,R.color.transparent);
        }

        helper.addOnClickListener(R.id.imgBox);

    }


}
