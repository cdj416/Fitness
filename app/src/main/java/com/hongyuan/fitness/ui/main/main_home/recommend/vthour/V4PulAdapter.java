package com.hongyuan.fitness.ui.main.main_home.recommend.vthour;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public abstract class V4PulAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public V4PulAdapter(){
        super(R.layout.item_home_pul);
    }
    @Override
    protected void convert(BaseViewHolder helper, T item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.long_default).error(R.mipmap.long_default);
        Glide.with(mContext).load(getImg(item)).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.firstName,getName(item)).setText(R.id.secondName,getDes(item));

        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.startWith).setVisibility(View.VISIBLE);
            helper.getView(R.id.normWhith).setVisibility(View.VISIBLE);
            helper.getView(R.id.endWith).setVisibility(View.GONE);
        }else if(helper.getAdapterPosition() > 0 && helper.getAdapterPosition() < (getData().size() - 1)){
            helper.getView(R.id.startWith).setVisibility(View.GONE);
            helper.getView(R.id.endWith).setVisibility(View.GONE);
            helper.getView(R.id.normWhith).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.startWith).setVisibility(View.GONE);
            helper.getView(R.id.endWith).setVisibility(View.VISIBLE);
            helper.getView(R.id.normWhith).setVisibility(View.GONE);
        }

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
