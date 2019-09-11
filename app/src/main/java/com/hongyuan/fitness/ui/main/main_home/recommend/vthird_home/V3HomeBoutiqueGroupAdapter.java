package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class.VtwoGroupClassBeans;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3HomeBoutiqueGroupAdapter extends BaseQuickAdapter<VtwoGroupClassBeans.DataBean.ListBean, BaseViewHolder> {

    public V3HomeBoutiqueGroupAdapter(){
        super(R.layout.item_vthird_home_group_course);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoGroupClassBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCs_name()).setText(R.id.coursePeople,"300人参课")
        .setText(R.id.storeName,item.getOs_name());

        if(helper.getPosition() == 0){
            helper.setBackgroundRes(R.id.itemBg,R.drawable.shape_radius6_top_ffffff);
        }else{
            helper.setBackgroundColor(R.id.itemBg,mContext.getResources().getColor(R.color.color_FFFFFF));
        }

        helper.addOnClickListener(R.id.goDetails);
    }
}
