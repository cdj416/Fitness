package com.hongyuan.fitness.ui.person.fix;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course.MyPriviteCourseBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class PcourseReservationAdapter extends BaseQuickAdapter<MyPriviteCourseBeans.DataBean.ListBean, BaseViewHolder> {

    public PcourseReservationAdapter(){
        super(R.layout.item_six_reservation_pcourse);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPriviteCourseBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.courseName,item.getCp_name())
                .setText(R.id.kTime,getShowTime(item.getLast_kong_date()))
                .setText(R.id.haveNum,String.valueOf(item.getHave_num()))
                .setText(R.id.allNum,"/"+item.getOcp_num())
                .setText(R.id.coachName,"教练："+item.getCoach_nickname()+"/"+item.getOs_name());

        if(item.getOs_n() == 2){
            helper.setBackgroundColor(R.id.reservation,mContext.getResources().getColor(R.color.color_FFFFFF))
                    .setTextColor(R.id.reservation,mContext.getResources().getColor(R.color.color_FF333333))
                    .setText(R.id.reservation,"教练暂未约课");
        }else{
            helper.setBackgroundRes(R.id.reservation,R.drawable.shape_radius16_ef5b48)
                    .setTextColor(R.id.reservation,mContext.getResources().getColor(R.color.color_FFFFFF));

            if(item.getHave_num() > 0){
                helper.setText(R.id.reservation,"去预约");
            }else{
                helper.setText(R.id.reservation,"再次购买");
            }
        }

        helper.addOnClickListener(R.id.reservation).addOnClickListener(R.id.goBuyBox);
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
