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
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.home_topic_one).error(R.mipmap.home_topic_one);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        if(helper.getAdapterPosition() == 0){
            Glide.with(mContext).load(R.mipmap.home_topic_one).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));
        }
        if(helper.getAdapterPosition() == 1){
            Glide.with(mContext).load(R.mipmap.home_topic_two).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));
        }
        if(helper.getAdapterPosition() == 2){
            Glide.with(mContext).load(R.mipmap.home_topic_third).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));
        }
        if(helper.getAdapterPosition() == 3){
            Glide.with(mContext).load(R.mipmap.home_topic_thorh).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));
        }


        helper.setText(R.id.firstName,getName(item)).setText(R.id.secondName,getDes(item))
                .setText(R.id.nums,getNums(item)+"人参与");

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
    /*
     * 参与人数
     * */
    public abstract int getNums(T item);
}
