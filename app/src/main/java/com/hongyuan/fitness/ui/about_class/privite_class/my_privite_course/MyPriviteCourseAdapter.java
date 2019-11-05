package com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MyPriviteCourseAdapter extends BaseQuickAdapter<MyPriviteCourseBeans.DataBean.ListBean, BaseViewHolder> {

    public MyPriviteCourseAdapter() {
        super(R.layout.item_myprivite_course);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPriviteCourseBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.courseImg));

        helper.setText(R.id.courseName,item.getCp_name()).setText(R.id.userNum,"已上"+(item.getBnum()-item.getHave_num())+"节 / 共"+item.getBnum()+"节")
                .setText(R.id.coachName,"教练："+item.getCoach_nickname());

        if(item.getHave_num() > 0){
            helper.setText(R.id.reservation,"预约");
        }else{
            helper.setText(R.id.reservation,"再次购买");
        }

        helper.addOnClickListener(R.id.reservation);
    }
}
