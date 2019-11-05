package com.hongyuan.fitness.ui.about_class.coach.coach_homepage;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.ui.about_class.privite_class.course_list.CouseListBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class CoachHomePageCouseListAdapter extends BaseQuickAdapter<CouseListBean.DataBean.ListBean, BaseViewHolder> {

    public CoachHomePageCouseListAdapter() {
        super(R.layout.item_coach_homepage_course_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouseListBean.DataBean.ListBean item) {
        String imgUrl = item.getCp_img();
        if(!imgUrl.contains("http")){
            imgUrl = Constants.ADRESS + item.getCp_img();
        }
        //RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img).centerCrop();
        //Glide.with(mContext).load(imgUrl).apply(options).into((RoundedImageView)helper.getView(R.id.bgImg));

        helper.setText(R.id.classType,item.getCp_name()).setText(R.id.classBewrite,item.getCp_info())
                .setText(R.id.coachPrice,item.getCp_price());

        helper.addOnClickListener(R.id.courseBox);
    }
}
