package com.hongyuan.fitness.ui.store;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.private_lessons.PrivateLessonsBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class StoreStarCoachAdapter extends BaseQuickAdapter<PrivateLessonsBean.DataBean.ListBean, BaseViewHolder> {

    public StoreStarCoachAdapter(){
        super(R.layout.item_star_coach);
    }
    @Override
    protected void convert(BaseViewHolder helper, PrivateLessonsBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.coachName,item.getCoach_nickname())
                .setText(R.id.classHours,"累计上课"+BaseUtil.getNoZoon(item.getTotal_course())+"节")
                .setText(R.id.classPrice, BaseUtil.getNoZoon(item.getMin_cp_price())+"/课时")
                .setText(R.id.bewrite,item.getCoach_experience());

        helper.addOnClickListener(R.id.jumpBox);
    }
}
