package com.hongyuan.fitness.ui.store.store_list;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreListImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public StoreListImageAdapter() {
        super(R.layout.item_store_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2);
        Glide.with(mContext).load(item).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.addOnClickListener(R.id.img);
    }
}
