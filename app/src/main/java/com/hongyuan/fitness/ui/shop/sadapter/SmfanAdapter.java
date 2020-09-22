package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.SmFanBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SmfanAdapter extends BaseQuickAdapter<SmFanBeans.DataBean.ListBean, BaseViewHolder> {

    public SmfanAdapter(){
        super(R.layout.item_message_fan);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmFanBeans.DataBean.ListBean item) {
        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.topHeight).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.topHeight).setVisibility(View.GONE);
        }

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.setText(R.id.fanName,item.getM_name()+" 关注了你")
                .setText(R.id.timeText, TimeUtil.formatDate(item.getMsg_time(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatXieMDHM));

        helper.addOnClickListener(R.id.goDetail);
    }
}
