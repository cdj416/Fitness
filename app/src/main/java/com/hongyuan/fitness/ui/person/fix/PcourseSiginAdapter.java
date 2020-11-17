package com.hongyuan.fitness.ui.person.fix;

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
        int endtime = TimeUtil.getOffectMinutes(item.getEnd_time(),TimeUtil.dateFormatYMDHMS);

        if(time > 0){
            helper.getView(R.id.qdBox).setVisibility(View.VISIBLE);
            helper.getView(R.id.signSuccess).setVisibility(View.GONE);

            helper.setText(R.id.showTime,"未到课程签到时间");
            helper.getView(R.id.qdText).setVisibility(View.GONE);
            helper.getView(R.id.cancelSign).setVisibility(View.VISIBLE);

            helper.addOnClickListener(R.id.cancelSign);
        }else if(time <= 0 && time >= -4320){


            if(item.getXy_qd_state() != 1 && endtime >= 0){
                helper.getView(R.id.qdBox).setVisibility(View.VISIBLE);
                helper.getView(R.id.signSuccess).setVisibility(View.GONE);

                helper.setText(R.id.showTime,"课程已开始");

                helper.getView(R.id.qdText).setVisibility(View.VISIBLE);
                helper.getView(R.id.cancelSign).setVisibility(View.VISIBLE);

                helper.addOnClickListener(R.id.cancelSign).addOnClickListener(R.id.qdText);
            }else if(item.getXy_qd_state() != 1 && endtime < 0){
                helper.getView(R.id.qdBox).setVisibility(View.VISIBLE);
                helper.getView(R.id.signSuccess).setVisibility(View.GONE);

                helper.setText(R.id.showTime,"课程已结束\n请在三天内完成签到");

                helper.getView(R.id.qdText).setVisibility(View.VISIBLE);
                helper.getView(R.id.cancelSign).setVisibility(View.VISIBLE);

                helper.addOnClickListener(R.id.cancelSign).addOnClickListener(R.id.qdText);

            }else{
                helper.getView(R.id.qdBox).setVisibility(View.GONE);
                helper.getView(R.id.signSuccess).setVisibility(View.VISIBLE);
                if(endtime >= 0){
                    helper.setText(R.id.succeTV,"课程已开始");
                }else{
                    helper.setText(R.id.succeTV,"课程已结束");
                }
            }

        }else{
            helper.getView(R.id.qdBox).setVisibility(View.GONE);
            helper.getView(R.id.signSuccess).setVisibility(View.GONE);

            helper.getView(R.id.qdText).setVisibility(View.VISIBLE);
            helper.getView(R.id.cancelSign).setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.cancelSign).addOnClickListener(R.id.qdText);
        }
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
