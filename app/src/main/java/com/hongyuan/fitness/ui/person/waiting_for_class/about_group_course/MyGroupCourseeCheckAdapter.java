package com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course;


import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyGroupCourseeCheckAdapter extends BaseQuickAdapter<MyGroupClassBean.DataBean.ListBean, BaseViewHolder> {

    public MyGroupCourseeCheckAdapter() {
        super(R.layout.item_mygroup_course_check);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGroupClassBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCs_name()).setText(R.id.courseStartTime, showTimeText(item.getCs_start_date(),item.getCs_end_date()))
            .setText(R.id.courseType,item.getSi_name());

        if(item.getXy_qd_state() == 1){
            helper.getView(R.id.qdBox).setClickable(false);
            helper.setText(R.id.checkIn,"已签到").setBackgroundRes(R.id.qdBox,R.drawable.shape_radius6_bottom_cccccc);
            helper.getView(R.id.showTime).setVisibility(View.GONE);
            helper.setTag(R.id.goBuyBox,false);
        }else{
            int time = TimeUtil.getOffectMinutes(item.getCs_start_date(),TimeUtil.dateFormatYMDHMS);

            if(time > 0 && time < 60){
                helper.getView(R.id.qdBox).setClickable(true);
                helper.setText(R.id.checkIn,"签到")
                        .setBackgroundRes(R.id.qdBox,R.drawable.shape_bottom_radius6_ef5b48)
                        .setText(R.id.showTime,"距离开课还有"+time+"分钟");
                helper.getView(R.id.showTime).setVisibility(View.VISIBLE);

                helper.addOnClickListener(R.id.qdBox);
                helper.setTag(R.id.goBuyBox,true);
            }else if(time < 0){
                helper.getView(R.id.qdBox).setClickable(false);
                helper.setText(R.id.checkIn,"未签到").setBackgroundRes(R.id.qdBox,R.drawable.shape_radius6_bottom_cccccc);
                helper.getView(R.id.showTime).setVisibility(View.GONE);
                helper.setTag(R.id.goBuyBox,false);
            }else{
                helper.getView(R.id.qdBox).setClickable(false);
                helper.setText(R.id.checkIn,"签到(开课前一小时可签到)").setBackgroundRes(R.id.qdBox,R.drawable.shape_radius6_bottom_cccccc);
                helper.getView(R.id.showTime).setVisibility(View.GONE);
                helper.setTag(R.id.goBuyBox,true);
            }
        }

        helper.addOnClickListener(R.id.goBuyBox);
    }

    private String showTimeText(String startData,String endDate){
        String showText;
        if(TimeUtil.isToday(startData,TimeUtil.dateFormatYMDHMS)){
            showText = "今天\t" + TimeUtil.formatDate(startData,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                    +"-"+TimeUtil.formatDate(endDate,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }else{
            showText = TimeUtil.formatDate(startData,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                    +"\t"+TimeUtil.formatDate(startData,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                    +"-"+TimeUtil.formatDate(endDate,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }
        return showText;
    }
}
