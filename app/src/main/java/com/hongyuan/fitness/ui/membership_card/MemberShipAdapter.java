package com.hongyuan.fitness.ui.membership_card;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.TimeUtil;

public class MemberShipAdapter extends BaseQuickAdapter<MyMemberShipBeans.DataBean.ListBean, BaseViewHolder> {

    public MemberShipAdapter(){
        super(R.layout.item_membership_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyMemberShipBeans.DataBean.ListBean item) {
        //RequestOptions options = new RequestOptions().placeholder(R.mipmap.year_card_bg_img).error(R.mipmap.year_card_bg_img);
        //Glide.with(mContext).load(item.getCard_img()).apply(options).into((RoundedImageView)helper.getView(R.id.cardBg));
        helper.setText(R.id.cardTypeText,item.getCard_type_name())
                .setText(R.id.cardUseTime, TimeUtil.getStringByFormat(item.getMy_card_date(),TimeUtil.dateFormatYMD) +"到期");


        helper.addOnClickListener(R.id.cardBox);
    }
}
