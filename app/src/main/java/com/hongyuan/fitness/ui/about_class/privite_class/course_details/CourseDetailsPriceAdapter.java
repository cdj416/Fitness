package com.hongyuan.fitness.ui.about_class.privite_class.course_details;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class CourseDetailsPriceAdapter extends BaseQuickAdapter<CourseDetailsBean.DataBean.PriceListBean, BaseViewHolder> {

    public CourseDetailsPriceAdapter(){
        super(R.layout.item_course_details_price_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseDetailsBean.DataBean.PriceListBean item) {
        helper.setText(R.id.courseNum,item.getMin_num()+"节课：¥")
                .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getPrice()));
    }
}
