package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.vthour.V4HomeBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3HomeStoreAdapter extends BaseQuickAdapter<V4HomeBeans.DataBean.OfflineStoreBean, BaseViewHolder> {

    public V3HomeStoreAdapter(){
        super(R.layout.item_home_store);
    }
    @Override
    protected void convert(BaseViewHolder helper, V4HomeBeans.DataBean.OfflineStoreBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getOs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.storeImg));

        helper.setText(R.id.distanceText,item.getJuli()).setText(R.id.storeName,item.getOs_name());
        helper.addOnClickListener(R.id.jumpBox);

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
    }
}
