package com.hongyuan.fitness.ui.membership_card.my_card_detail;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.membership_card.vtwo_my_card_list.VtwoMycardStoreList;

public class MyCardDetailsAdapter extends BaseQuickAdapter<VtwoMycardStoreList.DataBean.ListBean, BaseViewHolder> {

    public MyCardDetailsAdapter(){
        super(R.layout.item_card_store_name);
    }
    @Override
    protected void convert(BaseViewHolder helper, VtwoMycardStoreList.DataBean.ListBean item) {

        helper.setText(R.id.cardUseStore,item.getOs_name()).addOnClickListener(R.id.cardUseStore);
    }
}
