package com.hongyuan.fitness.ui.about_class.privite_class.course_list;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class CouseListAdapter extends BaseQuickAdapter<CouseListBean.DataBean.ListBean, BaseViewHolder> {

    public CouseListAdapter() {
        super(R.layout.item_course_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouseListBean.DataBean.ListBean item) {
        /*String imgUrl = item.getCp_img();
        if(!imgUrl.contains("http")){
            imgUrl = Constants.ADRESS + item.getCp_img();
        }*/
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getCp_img()).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.classTime,"可约时间："+item.getCp_duration())
                .setText(R.id.courseName,item.getCp_name())
                .setText(R.id.classInfo,item.getCp_info())
                .setText(R.id.coursePrice, BaseUtil.getNoZoon(item.getCp_price()));

        helper.addOnClickListener(R.id.courseBox);
    }
}
