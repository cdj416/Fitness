package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ShopNextCetegoryBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SMenuRightAdapter extends BaseQuickAdapter<ShopNextCetegoryBeans.DataBean, BaseViewHolder> {

    public SMenuRightAdapter(){
        super(R.layout.item_shop_menu_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopNextCetegoryBeans.DataBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getCategory_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.setText(R.id.name,item.getCategory_name());

        helper.addOnClickListener(R.id.rMenuBox);
    }
}
