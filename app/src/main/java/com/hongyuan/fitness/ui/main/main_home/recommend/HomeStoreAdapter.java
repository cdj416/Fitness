package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.store.more_store.StoreBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeStoreAdapter extends BaseQuickAdapter<StoreBean.DataBean.ListBean, BaseViewHolder> {

    public HomeStoreAdapter(){
        super(R.layout.item_home_store);
    }
    @Override
    protected void convert(BaseViewHolder helper, StoreBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2);
        Glide.with(mContext).load(item.getOs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.storeImg));

        helper.setText(R.id.distanceText,item.getJuli()).setText(R.id.storeName,item.getOs_name());
        helper.addOnClickListener(R.id.jumpBox);
    }
}
