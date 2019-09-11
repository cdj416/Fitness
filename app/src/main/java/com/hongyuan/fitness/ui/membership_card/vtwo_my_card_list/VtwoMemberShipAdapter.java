package com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class VtwoMemberShipAdapter extends BaseQuickAdapter<VtwoMycardCommonBeans, BaseViewHolder> {

    public VtwoMemberShipAdapter(){
        super(R.layout.item_membership_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoMycardCommonBeans item) {
        if(item.getMy_card_type() == 1){
            helper.setImageResource(R.id.cardBg,R.mipmap.store_dan_card)
                    .setText(R.id.cardTypeText,item.getOs_name())
                    .setText(R.id.cardUseTime, TimeUtil.formatDate(item.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
        }else{
            if(item.getOsl_id() == 1){
                helper.setImageResource(R.id.cardBg,R.mipmap.commont_card)
                        .setText(R.id.cardTypeText,"普通店通卡")
                        .setText(R.id.cardUseTime,TimeUtil.formatDate(item.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
            }else if(item.getOsl_id() == 2){
                helper.setImageResource(R.id.cardBg,R.mipmap.store_shenji_card)
                        .setText(R.id.cardTypeText,"升级店通卡")
                        .setText(R.id.cardUseTime,TimeUtil.formatDate(item.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
            }else{
                helper.setImageResource(R.id.cardBg,R.mipmap.zhuanshi_card)
                        .setText(R.id.cardTypeText,"砖石店通卡")
                        .setText(R.id.cardUseTime,TimeUtil.formatDate(item.getLast_date(),TimeUtil.dateFormatYMDHMS,TimeUtil.dateFormatDotYMD)+"到期");
            }

        }

        if(!BaseUtil.isValue(item.getLast_date())){
            helper.setText(R.id.cardUseTime,"未开卡("+item.getMy_card_days()+")");
        }


        helper.addOnClickListener(R.id.cardBox).addOnClickListener(R.id.xuKaButton);
    }
}
