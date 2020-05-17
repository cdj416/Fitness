package com.hongyuan.fitness.ui.person.fix;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class CourseCompleteAdapter extends BaseQuickAdapter<MyPriviteCourseBeans.DataBean.ListBean, BaseViewHolder> {

    public CourseCompleteAdapter(){
        super(R.layout.item_six_course_compelet);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPriviteCourseBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.courseName,item.getCp_name())
                .setText(R.id.kTime,getShowTime(item.getLast_kong_date()))
                .setText(R.id.coachName,"教练："+item.getCoach_nickname()+"/"+item.getOs_name())
                .setText(R.id.allNum,item.getHave_num());


        helper.addOnClickListener(R.id.reservation);
    }

    /*
     * 课程开始时间处理
     * */
    private String getShowTime(String startTime){
        String showText;
        if(TimeUtil.isToday(startTime,TimeUtil.dateFormat)){
            showText = "可约时间：今天 "+TimeUtil.formatDate(startTime,TimeUtil.dateFormat,TimeUtil.dateFormatHM);
        }else{
            showText = "可约时间："+TimeUtil.formatDate(startTime,TimeUtil.dateFormat,TimeUtil.dateFormat);
        }
        return showText;
    }
}
