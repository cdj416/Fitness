package com.hongyuan.fitness.ui.encyclopedia;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class EncyclopediaAdapter extends BaseQuickAdapter<EncyclopediaBean.DataBean.ListBean, BaseViewHolder> {

    public EncyclopediaAdapter() {
        super(R.layout.item_enclopedia);
    }

    @Override
    protected void convert(BaseViewHolder helper, EncyclopediaBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getBaike_img()).apply(options).into((RoundedImageView)helper.getView(R.id.imgOrVideo));

        if(item.getBaike_type() == 2){
            helper.setText(R.id.videoTime,"视频");
        }else{
            helper.setText(R.id.videoTime,"图文");
        }
        helper.setText(R.id.content,item.getBaike_name()).setText(R.id.tvSign,item.getFt_name());

        helper.addOnClickListener(R.id.box);
    }
}
