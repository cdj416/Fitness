package com.hongyuan.fitness.ui.find.more_topic;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.encyclopedia.EncyclopediaBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class MoreTopicAdapter extends BaseQuickAdapter<TopicBeans.DataBean.ListBean, BaseViewHolder> {

    public MoreTopicAdapter() {
        super(R.layout.item_more_topic);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getCategory_img()).apply(options).into((RoundedImageView)helper.getView(R.id.topicImg));

        helper.setText(R.id.topicName,item.getCategory_name()).setText(R.id.personNum,item.getCount()+"人");

        helper.addOnClickListener(R.id.box);
    }
}
