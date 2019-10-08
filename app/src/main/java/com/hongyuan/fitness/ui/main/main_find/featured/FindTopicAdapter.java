package com.hongyuan.fitness.ui.main.main_find.featured;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.find.topic.SlectTopicLeftBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class FindTopicAdapter extends BaseQuickAdapter<SlectTopicLeftBeans.DataBean.ListBean, BaseViewHolder> {

    public FindTopicAdapter(){
        super(R.layout.item_find_top);
    }

    @Override
    protected void convert(BaseViewHolder helper, SlectTopicLeftBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getCategory_img()).apply(options).into((RoundedImageView)helper.getView(R.id.topicImg));
        helper.setText(R.id.topicName,item.getCategory_name()).setText(R.id.personNum,item.getCount()+"人");

        if(helper.getPosition() == 0){
            helper.getView(R.id.firstView).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.firstView).setVisibility(View.GONE);
        }
        if(helper.getPosition() == (getData().size() - 1) ){
            helper.getView(R.id.lastView).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.lastView).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.jumpBox);
    }
}
