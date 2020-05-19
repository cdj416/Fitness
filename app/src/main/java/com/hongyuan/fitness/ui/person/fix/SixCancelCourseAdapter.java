package com.hongyuan.fitness.ui.person.fix;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SixCancelCourseAdapter extends BaseQuickAdapter<PriviteCourseCheckBeans.DataBean.ListBean, BaseViewHolder> {

    public SixCancelCourseAdapter(){
        super(R.layout.item_six_cancel_course);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriviteCourseCheckBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.courseStartTime,getShowTime(item.getStart_time(),item.getEnd_time()))
                .setText(R.id.showTime,TimeUtil.formatDate(item.getAdd_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD))
                .setText(R.id.coachName,"教练："+item.getCoach_nickname()+"/"+item.getOs_name())
                .setText(R.id.courseName,item.getCp_name());
    }

    /*
     * 课程时间处理
     * */
    private String getShowTime(String startTime,String endTime){
        String showText;
        if(TimeUtil.isToday(startTime,TimeUtil.dateFormatYMDHMS)){
            showText = "上课时间：今天 "
                    +TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                    + "-" +TimeUtil.formatDate(endTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }else{
            showText = "上课时间："
                    +TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMDHM)
                    + "-" +TimeUtil.formatDate(endTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }
        return showText;
    }
}
