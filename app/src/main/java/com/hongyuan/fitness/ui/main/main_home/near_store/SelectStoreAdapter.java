package com.hongyuan.fitness.ui.main.main_home.near_store;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class SelectStoreAdapter extends BaseQuickAdapter<StoreBean.DataBean.ListBean, BaseViewHolder> {

    public SelectStoreAdapter(){
        super(R.layout.item_nearby_stores);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean.DataBean.ListBean item) {

        Glide.with(mContext).load(item.getOs_img()).into((RoundedImageView) helper.getView(R.id.nearImg));
        Glide.with(mContext).load(item.getOsl_img()).into((ImageView) helper.getView(R.id.nearMarkImg));

        helper.setText(R.id.storeName,item.getOs_name()).setText(R.id.businessHours,item.getOs_start_time()+"-"+item.getOs_end_time())
                .setText(R.id.distance,item.getJuli()).setText(R.id.price,"会籍卡"+item.getOs_area_privite()+"起");


        helper.addOnClickListener(R.id.jumpBox);
    }
}
