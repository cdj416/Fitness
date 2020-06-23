package com.hongyuan.fitness.ui.main.main_home.recommend.vthour;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class V4YueYundongAdapter extends BaseQuickAdapter<HomeIndexBeans.DataBean.SportBean, BaseViewHolder> {

    public V4YueYundongAdapter(){
        super(R.layout.item_home_yue_sports);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeIndexBeans.DataBean.SportBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.long_default).error(R.mipmap.long_default);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.title,item.getState_name()+":"+item.getGs_declareation())
                .setText(R.id.sponsorName,item.getMi_realname()).setText(R.id.addNum,"已约"+item.getCount()+"/"+item.getGs_people_num())
                .setText(R.id.payType,"费用:"+item.getFee_type_name());

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
}
