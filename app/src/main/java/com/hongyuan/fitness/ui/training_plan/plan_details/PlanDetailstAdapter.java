package com.hongyuan.fitness.ui.training_plan.plan_details;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class PlanDetailstAdapter extends BaseQuickAdapter<PlanDetailsContentBeans.DataBean.ListBean, BaseViewHolder> {

    public PlanDetailstAdapter(){
        super(R.layout.item_plan_details);
    }


    @Override
    protected void convert(BaseViewHolder helper, PlanDetailsContentBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_head_img).error(R.mipmap.default_head_img);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));

        helper.setText(R.id.coachName,item.getM_name()).setText(R.id.goodAt,item.getFt_str())
                .setText(R.id.storeName,item.getOs_name());

        helper.addOnClickListener(R.id.goNext);
    }
}
