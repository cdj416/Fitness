package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ShopMenuBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SMenuRightChildAdapter extends BaseQuickAdapter<ShopMenuBeans.DataBean.SecondBean, BaseViewHolder> {

    public SMenuRightChildAdapter(){
        super(R.layout.item_shop_menus_child);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopMenuBeans.DataBean.SecondBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getCategory_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.setText(R.id.name,item.getCategory_name());

        helper.addOnClickListener(R.id.rMenuBox);
    }
}
