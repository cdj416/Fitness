package com.hongyuan.fitness.ui.about_class.coach.coach_list;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_home.recommend.vtwo_home.VtwoStarCoachBean;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class CoachListAdapter extends BaseQuickAdapter<VtwoStarCoachBean.DataBean.ListBean, BaseViewHolder> {

    public CoachListAdapter(){
        super(R.layout.item_coach_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoStarCoachBean.DataBean.ListBean item) {
        //helper.setText(R.id.mainName,item.getMainName()).setText(R.id.mark,item.getMark());

        Glide.with(mContext).load(item.getCoach_head()).into((RoundedImageView)helper.getView(R.id.coachHeadImg));

        helper.setText(R.id.storeName,"门店： "+item.getOs_name())
                .setText(R.id.courseTime,"可约时间："+showTimeText(item.getLast_kong_date()))
                .setText(R.id.coachName,item.getCoach_nickname())
                .setText(R.id.coachType,"擅长："+item.getFt_str())
                .setText(R.id.coursePrice,String.valueOf(item.getCp_price()));

        helper.addOnClickListener(R.id.jumpBox);
    }

    /*
    * 处理时间的显示
    * */
    private String showTimeText(String time){
        if(TimeUtil.isToday(time,TimeUtil.dateFormat)){
            return "今天"+TimeUtil.formatDate(time,TimeUtil.dateFormat,TimeUtil.dateFormatHM);
        }
        return TimeUtil.formatDate(time,TimeUtil.dateFormat,TimeUtil.dateFormatMDHM);
    }
}
