package com.hongyuan.fitness.ui.about_class.group_class.group_list;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.main.main_about_class.group_class.MyGroupClassBean;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyGroupCourseeAdapter extends BaseQuickAdapter<MyGroupClassBean.DataBean.ListBean, BaseViewHolder> {

    public MyGroupCourseeAdapter() {
        super(R.layout.item_mygroup_course_check);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyGroupClassBean.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getCs_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.courseName,item.getCs_name()).setText(R.id.courseTime,
                TimeUtil.formatDate(item.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatMDofChinese)
                +"\t"+TimeUtil.formatDate(item.getCs_start_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM)
                +"-"+TimeUtil.formatDate(item.getCs_end_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatHM))
            .setText(R.id.courseType,item.getSi_name());
    }
}
