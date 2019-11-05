package com.hongyuan.fitness.ui.about_class.coach.cocah_img;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CocahImageAdapter extends BaseQuickAdapter<CoachImgBeans.DataBean.ListBean, BaseViewHolder> {

    public CocahImageAdapter() {
        super(R.layout.item_store_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoachImgBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
        Glide.with(mContext).load(item.getImg_src()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.addOnClickListener(R.id.img);
    }
}
