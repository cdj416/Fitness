package com.hongyuan.fitness.ui.about_class.privite_class.my_privite_course;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class SelectCoachAdapter extends BaseQuickAdapter<MyCoachBeans.DataBean.ListBean, BaseViewHolder> {

    public SelectCoachAdapter() {
        super(R.layout.item_select_coach);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCoachBeans.DataBean.ListBean item) {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_test2).error(R.mipmap.a_test2).centerCrop();
        Glide.with(mContext).load(item.getCoach_head()).apply(options).into((RoundedImageView)helper.getView(R.id.headImg));
        helper.setText(R.id.coachName,item.getCoach_nickname());

        if(item.isSelect()){
            helper.setTextColor(R.id.coachName,0xFF333333).setVisible(R.id.mengCheng,false);
        }else{
            helper.setTextColor(R.id.coachName,0xFF999999).setVisible(R.id.mengCheng,true);
        }

        helper.addOnClickListener(R.id.selectBox);
    }
}
