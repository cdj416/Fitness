package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.SmPraiseBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SmPraiseAdapter extends BaseQuickAdapter<SmPraiseBeans.DataBean.ListBean, BaseViewHolder> {

    public SmPraiseAdapter(){
        super(R.layout.item_message_praise);
    }

    @Override
    protected void convert(BaseViewHolder helper, SmPraiseBeans.DataBean.ListBean item) {
        if(helper.getAdapterPosition() == 0){
            helper.getView(R.id.topHeight).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.topHeight).setVisibility(View.GONE);
        }

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mContext).load(item.getMi_head()).apply(options).into((RoundedImageView)helper.getView(R.id.img));

        helper.setText(R.id.praiseName,item.getM_name()+" 赞了你的帖子")
                .setText(R.id.praiseNum,"+"+item.getPraise_num());

        helper.addOnClickListener(R.id.goDetail);

    }
}
