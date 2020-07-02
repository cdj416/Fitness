package com.hongyuan.fitness.ui.person.waiting_for_class.about_group_course;


import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;
import com.hongyuan.fitness.util.TimeUtil;

public class MyGroupCourseeCheckAdapter extends BaseQuickAdapter<MyGroupClassBean.DataBean.ListBean, BaseViewHolder> {

    public MyGroupCourseeCheckAdapter() {
        super(R.layout.item_mygroup_course_check);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGroupClassBean.DataBean.ListBean item) {

        helper.setText(R.id.courseName,item.getCs_name()).setText(R.id.courseStartTime, item.getOcs_number() > 0 ? showTimeText(item.getCs_start_date(),item.getCs_end_date()) +"\t#"+item.getOcs_number() : showTimeText(item.getCs_start_date(),item.getCs_end_date()))
            .setText(R.id.courseType,item.getOs_name()+"/"+item.getSi_name());

        if(item.getXy_qd_state() == 1){
            helper.getView(R.id.checkIn).setClickable(false);
            helper.setText(R.id.checkIn,"已签到").setBackgroundRes(R.id.checkIn,R.drawable.shape_radius24_cccccc);
            helper.getView(R.id.showTime).setVisibility(View.GONE);
        }else{
            int time = TimeUtil.getOffectMinutes(item.getCs_start_date(),TimeUtil.dateFormatYMDHMS);

            if(time > 0 && time < 30){
                helper.getView(R.id.checkIn).setClickable(true);
                helper.setBackgroundRes(R.id.checkIn,R.drawable.shape_radius16_ef5b48)
                        .setText(R.id.showTime,"距离开课还有"+time+"分钟");
                helper.getView(R.id.showTime).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.checkIn);
            }else if(time < 0){
                helper.getView(R.id.checkIn).setClickable(false);
                helper.setText(R.id.checkIn,"未签到").setBackgroundRes(R.id.checkIn,R.drawable.shape_radius24_cccccc);
                helper.getView(R.id.showTime).setVisibility(View.GONE);
            }else{
                helper.getView(R.id.checkIn).setClickable(false);
                helper.setText(R.id.checkIn,"扫码签到").setBackgroundRes(R.id.checkIn,R.drawable.shape_radius24_cccccc);
                helper.getView(R.id.showTime).setVisibility(View.GONE);
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
