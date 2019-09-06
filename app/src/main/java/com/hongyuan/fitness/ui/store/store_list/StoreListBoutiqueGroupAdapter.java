package com.hongyuan.fitness.ui.store.store_list;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreListBoutiqueGroupAdapter extends BaseQuickAdapter<BoutiqueGroupBean.DataBean.ListBean, BaseViewHolder> {

    public StoreListBoutiqueGroupAdapter() {
        super(R.layout.item_group_class);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoutiqueGroupBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.className,item.getCs_name()).setText(R.id.classType,item.getSi_name())
        .setText(R.id.reserveNum,"已约"+item.getCs_sign_up_num()+"/"+item.getCs_max_num())
        .setText(R.id.signTime,TimeUtil.formatDate(item.getCs_start_date(), TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                +"\t"+TimeUtil.formatDate(item.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                +"-"+TimeUtil.formatDate(item.getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));

        helper.addOnClickListener(R.id.jumpBox);
    }
}
