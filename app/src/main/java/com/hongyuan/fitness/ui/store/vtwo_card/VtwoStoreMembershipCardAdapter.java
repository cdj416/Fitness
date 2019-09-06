package com.hongyuan.fitness.ui.store.vtwo_card;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class VtwoStoreMembershipCardAdapter extends BaseQuickAdapter<FixedCardBeans, BaseViewHolder> {

    public VtwoStoreMembershipCardAdapter(){
        super(R.layout.item_vtwo_store_membership_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, FixedCardBeans item) {
        if(item.getCardType() == 1){
            helper.setImageResource(R.id.cardBg,R.mipmap.store_dan_card)
                    .setText(R.id.storeName,item.getOs_name());
        }else{
            if(item.getOsl_id() == 1){
                helper.setImageResource(R.id.cardBg,R.mipmap.commont_card)
                        .setText(R.id.storeName,"普通店通卡");
            }else if(item.getOsl_id() == 2){
                helper.setImageResource(R.id.cardBg,R.mipmap.store_shenji_card)
                        .setText(R.id.storeName,"升级店通卡");
            }else{
                helper.setImageResource(R.id.cardBg,R.mipmap.zhuanshi_card)
                        .setText(R.id.storeName,"砖石店通卡");
            }

        }
        helper.addOnClickListener(R.id.cardBox);
    }
}
