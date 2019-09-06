package com.hongyuan.fitness.ui.main.main_home.recommend;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons.VtwoPrivateLessonsBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class HomePriviteCourseAdapter extends BaseQuickAdapter<VtwoPrivateLessonsBeans.DataBean.ListBean, BaseViewHolder> {

    public HomePriviteCourseAdapter(){
        super(R.layout.item_home_privite_course);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoPrivateLessonsBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_testbaner3).error(R.mipmap.a_testbaner3);
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.mainName,item.getCp_name()).setText(R.id.coachName,item.getCoach_nickname())
        .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getCp_price()));

        helper.addOnClickListener(R.id.jumpBox);
    }
}
