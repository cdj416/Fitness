package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.ShopNextCetegoryBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class SNMenuAdapter extends BaseQuickAdapter<ShopNextCetegoryBeans.DataBean.ListBean, BaseViewHolder> {

    public SNMenuAdapter(){
        super(R.layout.item_shop_next_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopNextCetegoryBeans.DataBean.ListBean item) {
        RoundedImageView imgView = helper.getView(R.id.menuImg);

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        if(helper.getAdapterPosition() != (getData().size() - 1)){
            Glide.with(mContext).load(item.getCategory_img()).apply(options).into(imgView);
        }else{
            Glide.with(mContext).load(R.mipmap.gray_more_img).apply(options).into(imgView);
        }


        helper.setText(R.id.menuText,item.getCategory_name());


        if(item.isSelect()){
            helper.setTextColor(R.id.menuText,mContext.getResources().getColor(R.color.color_EF5B48));
            imgView.setBorderColor(mContext.getResources().getColor(R.color.color_EF5B48));
            imgView.setBorderWidth(1f);
        }else{
            helper.setTextColor(R.id.menuText,mContext.getResources().getColor(R.color.color_FF333333));
            imgView.setBorderColor(mContext.getResources().getColor(R.color.color_f2));
            imgView.setBorderWidth(1f);
        }

        helper.addOnClickListener(R.id.menuBox);
    }
}
