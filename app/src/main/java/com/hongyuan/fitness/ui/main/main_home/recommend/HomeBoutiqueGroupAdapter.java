package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomeBoutiqueGroupAdapter extends BaseQuickAdapter<VtwoGroupClassBeans.DataBean.ListBean, BaseViewHolder> {

    public HomeBoutiqueGroupAdapter(){
        super(R.layout.item_home_boutique_group);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoGroupClassBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.home_meng_ban).error(R.mipmap.home_meng_ban);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.mainName,item.getCs_name()).setText(R.id.personNum,"300人参课")
        .setText(R.id.storeName,item.getSi_name());
        helper.addOnClickListener(R.id.jumpBox);
    }
}
