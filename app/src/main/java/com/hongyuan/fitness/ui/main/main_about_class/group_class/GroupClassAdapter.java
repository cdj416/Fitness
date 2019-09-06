package com.hongyuan.fitness.ui.main.main_about_class.group_class;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.BoutiqueGroupBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class GroupClassAdapter extends BaseQuickAdapter<BoutiqueGroupBean.DataBean.ListBean, BaseViewHolder> {

    public GroupClassAdapter(){
        super(R.layout.item_group_class);
    }
    @Override
    protected void convert(BaseViewHolder helper, BoutiqueGroupBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.className,item.getCs_name()).setText(R.id.classType,item.getSi_name())
                .setText(R.id.reserveNum,"已约"+item.getCs_sign_up_num()+"/"+item.getCs_max_num());

        helper.addOnClickListener(R.id.jumpBox);
    }
}
