package com.hongyuan.fitness.ui.person.my_collection.collection_baike;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CollectionBaiKeAdapter extends BaseQuickAdapter<CollectionBaikeBeans.DataBean.ListBean, BaseViewHolder> {

    public CollectionBaiKeAdapter() {
        super(R.layout.item_enclopedia);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBaikeBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getArticle_img()).apply(options).into((RoundedImageView)helper.getView(R.id.imgOrVideo));

        if(item.getType() == 2){
            helper.setText(R.id.videoTime,"视频");
        }else{
            helper.setText(R.id.videoTime,"图文");
        }
        helper.setText(R.id.content,item.getArticle_title()).setText(R.id.tvSign,item.getReview_num()+"条评论");

        helper.addOnClickListener(R.id.box);
    }
}
