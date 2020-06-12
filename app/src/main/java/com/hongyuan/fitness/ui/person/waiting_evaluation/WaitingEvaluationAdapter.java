package com.hongyuan.fitness.ui.person.waiting_evaluation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class WaitingEvaluationAdapter extends BaseQuickAdapter<PriviteCourseCheckBeans.DataBean.ListBean, BaseViewHolder> {

    public WaitingEvaluationAdapter(){
        super(R.layout.item_wait_evaluation);
    }
    @Override
    protected void convert(BaseViewHolder helper, PriviteCourseCheckBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCp_name()).setText(R.id.courseNum,"x"+item.getNum())
                .setText(R.id.coursePrice,"¥"+ BaseUtil.getNoZoon(item.getCp_price()))
                .setText(R.id.courseStartTime, TimeUtil.formatDate(item.getStart_time(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                        +"\t"+TimeUtil.formatDate(item.getStart_time(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                        +"-"+TimeUtil.formatDate(item.getEnd_time(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM));

        helper.addOnClickListener(R.id.goDetails).addOnClickListener(R.id.goEvaluation);
    }
}
