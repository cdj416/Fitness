package com.hongyuan.fitness.ui.main.main_about_class.private_lessons.vtwo_private_lessons;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class VtwoPrivateLessonsAdapter extends BaseQuickAdapter<VtwoPrivateLessonsBeans.DataBean.ListBean, BaseViewHolder> {

    public VtwoPrivateLessonsAdapter(){
        super(R.layout.item_vtwo_privatelessons);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoPrivateLessonsBeans.DataBean.ListBean item) {
        //helper.setText(R.id.mainName,item.getMainName()).setText(R.id.mark,item.getMark());

        Glide.with(mContext).load(item.getCp_img()).into((RoundedImageView)helper.getView(R.id.courseBgImg));
        Glide.with(mContext).load(item.getCoach_head()).into((RoundedImageView)helper.getView(R.id.coachHeadImg));

        helper.setText(R.id.courseTime,"可约时间：最近 "+showTimeText(item.getLast_kong_date()))
                .setText(R.id.courseName,item.getCp_name())
                .setText(R.id.coachNameAddress,item.getCoach_nickname() +" / " +item.getOs_name())
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
