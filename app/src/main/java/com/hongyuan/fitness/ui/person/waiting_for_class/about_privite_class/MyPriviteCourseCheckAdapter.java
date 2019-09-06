package com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class;


import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyPriviteCourseCheckAdapter extends BaseQuickAdapter<PriviteCourseCheckBeans.DataBean.ListBean, BaseViewHolder> {

    public MyPriviteCourseCheckAdapter() {
        super(R.layout.item_myprivite_course_check);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriviteCourseCheckBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCp_name()).setText(R.id.userNum,"共"+item.getNum()+"节")
                .setText(R.id.coachName,"教练："+item.getCoach_nickname())
                .setText(R.id.courseStartTime, getShowTime(item.getStart_time()));

        if(item.getXy_qd_state() == 1){
            helper.getView(R.id.qdBox).setClickable(false);
            helper.setText(R.id.checkIn,"已签到").setBackgroundRes(R.id.qdBox,R.drawable.shape_radius6_bottom_cccccc);
            helper.getView(R.id.showTime).setVisibility(View.GONE);
            helper.setTag(R.id.goBuyBox,false);
        }else{
            int time = TimeUtil.getOffectMinutes(item.getStart_time(),TimeUtil.dateFormatYMDHMS);

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

    /*
    * 课程开始时间处理
    * */
    private String getShowTime(String startTime){
        String showText;
        if(TimeUtil.isToday(startTime,TimeUtil.dateFormatYMDHMS)){
            showText = "今天 "+TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }else{
            showText = TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMDHM);
        }
        return showText;
    }
}
