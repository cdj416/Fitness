package com.hongyuan.fitness.ui.main.main_home.recommend.vthour;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class V4TopicAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public V4TopicAdapter(){
        super(R.layout.item_home_topic);
    }
    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.firstName,getName(item)).setText(R.id.secondName,getDes(item));

        helper.addOnClickListener(R.id.goDetail);
    }

    /*
     * 获取图片路径
     * */
    public abstract String getImg(T item);
    /*
     * 标题信息
     * */
    public abstract String getName(T item);
    /*
     * 描述信息
     * */
    public abstract String getDes(T item);
}
