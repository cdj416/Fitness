package com.hongyuan.fitness.ui.store.store_page_list;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreAdapter extends BaseQuickAdapter<StoreBean.DataBean.ListBean, BaseViewHolder> {

    public StoreAdapter(){
        super(R.layout.item_stores);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.fang_default).error(R.mipmap.fang_default);
        Glide.with(mContext).load(item.getOs_img()).apply(options).into((RoundedImageView) helper.getView(R.id.storeImg));
        if(BaseUtil.isValue(item.getOsl_img())){
            Glide.with(mContext).load(item.getOsl_img()).into((ImageView) helper.getView(R.id.storeMark));
            helper.getView(R.id.storeMark).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.storeMark).setVisibility(View.GONE);
        }


        helper.setText(R.id.storeName,item.getOs_name()).setText(R.id.businessHours,item.getOs_start_time()+"-"+item.getOs_end_time())
                .setText(R.id.distance,item.getJuli()).setText(R.id.cardPrice,"会籍卡"+item.getOs_area_privite()+"起");


        helper.addOnClickListener(R.id.jumpBox);
    }
}
