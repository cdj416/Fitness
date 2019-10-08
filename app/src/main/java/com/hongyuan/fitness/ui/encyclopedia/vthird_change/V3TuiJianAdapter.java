package com.hongyuan.fitness.ui.encyclopedia.vthird_change;


import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3TuiJianAdapter extends BaseQuickAdapter<BaikeTJBeans.DataBean.ListBean, BaseViewHolder> {

    public V3TuiJianAdapter() {
        super(R.layout.item_tj_enclopedia);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaikeTJBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getArticle_img()).apply(options).into((RoundedImageView)helper.getView(R.id.imgOrVideo));

        if(item.getType() == 2){
            helper.getView(R.id.playMark).setVisibility(View.VISIBLE);
            helper.setText(R.id.videoTime,"视频");
        }else{
            helper.getView(R.id.playMark).setVisibility(View.GONE);
            helper.setText(R.id.videoTime,"图文");
        }
        helper.setText(R.id.content,item.getArticle_title()).setText(R.id.tvSign,"#"+item.getBaike_category_name());

        helper.addOnClickListener(R.id.box);
    }
}
