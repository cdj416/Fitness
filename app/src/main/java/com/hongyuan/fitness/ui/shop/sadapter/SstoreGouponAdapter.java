package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;

public class SstoreGouponAdapter extends BaseQuickAdapter<BaseBean, BaseViewHolder> {

    public SstoreGouponAdapter(){
        super(R.layout.item_sstore_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBean item) {
        if(helper.getAdapterPosition() == (getData().size() - 1)){
            helper.getView(R.id.rWhith).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.rWhith).setVisibility(View.GONE);
        }
    }
}
