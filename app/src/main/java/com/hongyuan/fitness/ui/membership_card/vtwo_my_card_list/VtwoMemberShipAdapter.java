package com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.membership_card.v4_mycard_list.V4MyCardListBeans;
import com.hongyuan.fitness.util.TimeUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class VtwoMemberShipAdapter extends BaseQuickAdapter<V4MyCardListBeans.DataBean.ListBean, BaseViewHolder> {

    public VtwoMemberShipAdapter(){
        super(R.layout.item_membership_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, V4MyCardListBeans.DataBean.ListBean item) {

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.defaul_no_img).error(R.mipmap.defaul_no_img);
        Glide.with(mContext).load(item.getCard_img()).apply(options).into((RoundedImageView)helper.getView(R.id.cardBg));

        helper.setText(R.id.cardName,item.getCard_type_name())
                .setText(R.id.cardUseTime, TimeUtil.formatDate(item.getLast_date(),
                        TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");

        if(item.getIs_first() == 1){
            helper.setVisible(R.id.useMark,true);
        }else{
            helper.setVisible(R.id.useMark,false);
        }

        helper.addOnClickListener(R.id.cardBox).addOnClickListener(R.id.memberQr);
    }
}
