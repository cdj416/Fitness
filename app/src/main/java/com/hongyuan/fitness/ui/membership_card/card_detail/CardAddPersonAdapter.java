package com.hongyuan.fitness.ui.membership_card.card_detail;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;

public class CardAddPersonAdapter extends BaseQuickAdapter<CardAddPersonBeans, BaseViewHolder> {

    public CardAddPersonAdapter(){
        super(R.layout.item_card_add_person);
    }
    @Override
    protected void convert(BaseViewHolder helper, CardAddPersonBeans item) {

        helper.setText(R.id.name,item.getName()).setText(R.id.phoneOrDays,item.getPhoneOrDays());

        if(helper.getPosition() == 0){
            helper.getView(R.id.delImg).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.delImg).setVisibility(View.VISIBLE);
        }

        helper.addOnClickListener(R.id.editImg).addOnClickListener(R.id.delImg);
    }
}
