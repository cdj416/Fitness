package com.hongyuan.fitness.ui.person.fix;

import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.waiting_for_class.about_privite_class.PriviteCourseCheckBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class PcourseSiginAdapter extends BaseQuickAdapter<PriviteCourseCheckBeans.DataBean.ListBean, BaseViewHolder> {

    public PcourseSiginAdapter(){
        super(R.layout.item_six_sign_pcourse);
    }

    @Override
    protected void convert(BaseViewHolder helper, PriviteCourseCheckBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img).centerCrop();
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.courseName,item.getCp_name())
                .setText(R.id.coachName,"教练："+item.getCoach_nickname()+"/"+item.getOs_name())
                .setText(R.id.courseStartTime, getShowTime(item.getStart_time()));

        int time = TimeUtil.getOffectMinutes(item.getStart_time(),TimeUtil.dateFormatYMDHMS);
        if(time < -4320){
            helper.getView(R.id.qdBox).setVisibility(View.GONE);
            helper.getView(R.id.wcSignBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.wcCancelSign).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.wcCancelSign).setVisibility(View.VISIBLE);
        }

        if(item.getXy_qd_state() == 1){
            helper.getView(R.id.qdBox).setVisibility(View.GONE);
            helper.getView(R.id.wcSignBox).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.qdBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.wcSignBox).setVisibility(View.GONE);

            if((time > 0 && time < 60) || (time < 0 && time > -4320)){
                helper.getView(R.id.showTime).setVisibility(View.VISIBLE);
                if(time > 0 && time < 60){
                    helper.setText(R.id.showTime,"距离开课还有"+time+"分钟");
                }else{
                    helper.setText(R.id.showTime,"三天后自动完成签到");
                }
                helper.setBackgroundRes(R.id.qdText,R.drawable.shape_radius16_ef5b48);
                helper.getView(R.id.qdText).setClickable(true);
                helper.addOnClickListener(R.id.qdText);
            }else if(time > 60){
                helper.getView(R.id.showTime).setVisibility(View.VISIBLE);
                helper.setText(R.id.showTime,"开课前一小时可签到");

                helper.setBackgroundRes(R.id.qdText,R.drawable.shape_radius16_42000000);
                helper.getView(R.id.qdText).setClickable(false);
            }
        }

        helper.addOnClickListener(R.id.wcCancelSign)
                .addOnClickListener(R.id.cancelSign)
                .addOnClickListener(R.id.goSignDetail);
    }

    /*
     * 课程开始时间处理
     * */
    private String getShowTime(String startTime){
        String showText;
        if(TimeUtil.isToday(startTime,TimeUtil.dateFormatYMDHMS)){
            showText = "上课时间：今天 "+TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM);
        }else{
            showText = "上课时间："+TimeUtil.formatDate(startTime,TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormat);
        }
        return showText;
    }
}
