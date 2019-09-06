package com.hongyuan.fitness.ui.main.main_about_class.private_lessons;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.makeramen.roundedimageview.RoundedImageView;

public class PrivateLessonsAdapter extends BaseQuickAdapter<PrivateLessonsBean.DataBean.ListBean, BaseViewHolder> {

    public PrivateLessonsAdapter(){
        super(R.layout.item_private_lessons);
    }
    @Override
    protected void convert(BaseViewHolder helper, PrivateLessonsBean.DataBean.ListBean item) {
        //helper.setText(R.id.mainName,item.getMainName()).setText(R.id.mark,item.getMark());

        Glide.with(mContext).load(item.getCoach_head()).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.appointment,"可约时间："+item.getLast_kong_date())
                .setText(R.id.coachName,item.getMi_realname()).setText(R.id.coachType,item.getFt_names())
                .setText(R.id.coachPrice,String.valueOf(item.getMin_cp_price()));

        helper.addOnClickListener(R.id.jumpBox);
    }
}
