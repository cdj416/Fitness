package com.hongyuan.fitness.ui.main.main_about_class.group_class.vtwo_group_class;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;

public class VtwoGroupClassAdapter extends BaseQuickAdapter<VtwoGroupClassBeans.DataBean.ListBean, BaseViewHolder> {

    public VtwoGroupClassAdapter(){
        super(R.layout.item_vtwo_group_class);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoGroupClassBeans.DataBean.ListBean item) {
        //Glide.with(mContext).load(item.getCs_img()).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseTime,showTimeText(item.getCs_start_date(),item.getCs_end_date()))
                .setText(R.id.courseName,item.getCs_name())
                .setText(R.id.storeName,item.getOs_name())
                .setText(R.id.signButton,item.getState_name())
                .setText(R.id.signUpNum,item.getCs_sign_up_num()+"/"+item.getCs_max_num());


        helper.addOnClickListener(R.id.jumpBox);
    }

    /*
    * 获取前缀显示
    * */
    private String getWeek(String time){
        String startStr = "";
        if(TimeUtil.isToday(time,TimeUtil.dateFormat)){
            startStr += "今天" + " ";
        }
        startStr += TimeUtil.getTwoWeek();
        return startStr;
    }

    /*
    * 获取显示时间
    * */
    private String showTimeText(String startTime,String endTime){
        String showTime = getWeek(startTime);
        showTime+=" "+TimeUtil.formatDate(startTime,TimeUtil.dateFormat,TimeUtil.dateFormatHM);
        showTime+="-"+TimeUtil.formatDate(endTime,TimeUtil.dateFormat,TimeUtil.dateFormatHM);
        return showTime;
    }


}
