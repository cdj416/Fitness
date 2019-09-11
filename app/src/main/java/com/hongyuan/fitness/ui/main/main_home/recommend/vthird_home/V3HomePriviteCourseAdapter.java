package com.hongyuan.fitness.ui.main.main_home.recommend.vthird_home;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class V3HomePriviteCourseAdapter extends BaseQuickAdapter<VtwoPrivateLessonsBeans.DataBean.ListBean, BaseViewHolder> {

    public V3HomePriviteCourseAdapter(){
        super(R.layout.item_vthird_home_privite_course);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoPrivateLessonsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCp_name()).setText(R.id.coachName,item.getCoach_nickname())
        .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getCp_price()));

        if(helper.getPosition() == 0){
            helper.setBackgroundRes(R.id.itemBg,R.drawable.shape_radius6_top_ffffff);
        }else{
            helper.setBackgroundColor(R.id.itemBg,mContext.getResources().getColor(R.color.color_FFFFFF));
        }

        helper.addOnClickListener(R.id.itemBg);
    }
}
