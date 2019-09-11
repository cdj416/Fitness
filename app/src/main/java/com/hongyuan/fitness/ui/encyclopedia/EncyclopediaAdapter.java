package com.hongyuan.fitness.ui.encyclopedia;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class EncyclopediaAdapter extends BaseQuickAdapter<EncyclopediaBean.DataBean.ListBean, BaseViewHolder> {

    public EncyclopediaAdapter() {
        super(R.layout.item_enclopedia);
    }

    @Override
    protected void convert(BaseViewHolder helper, EncyclopediaBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getArticle_img()).apply(options).into((RoundedImageView)helper.getView(R.id.imgOrVideo));

        if(item.getType() == 2){
            helper.setText(R.id.videoTime,"视频");
        }else{
            helper.setText(R.id.videoTime,"图文");
        }
        helper.setText(R.id.content,item.getArticle_title()).setText(R.id.tvSign,item.getReview_num()+"条评论");

        if(BaseUtil.isValue(item.getNumMark()) && item.getNumMark() != 0){
            helper.setImageResource(R.id.numMark,item.getNumMark());
        }

        helper.addOnClickListener(R.id.box);
    }
}
