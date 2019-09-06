package com.hongyuan.fitness.ui.membership_card.renewal_card;

import android.graphics.Paint;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.membership_card.MyMemberShipBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.TimeUtil;

public class RenewalCardAdapter extends BaseQuickAdapter<RenewalCardBeans.DataBean.ListBean, BaseViewHolder> {

    public RenewalCardAdapter(){
        super(R.layout.item_renewal_card);
    }
    @Override
    protected void convert(BaseViewHolder helper, RenewalCardBeans.DataBean.ListBean item) {

        helper.setText(R.id.cardType,item.getCt_name())
                .setText(R.id.cardPrice, BaseUtil.getNoZoon(item.getCard_sale_price()))
                .setText(R.id.oldPrice,"￥"+item.getCard_original_price())
                .setText(R.id.giftDays,"("+item.getCard_note()+")");

        ((TextView)helper.getView(R.id.oldPrice)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );

        helper.addOnClickListener(R.id.cardBox);
    }
}
