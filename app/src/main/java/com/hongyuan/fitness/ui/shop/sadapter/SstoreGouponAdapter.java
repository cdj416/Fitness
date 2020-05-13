package com.hongyuan.fitness.ui.shop.sadapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.StoreCouponBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class SstoreGouponAdapter extends BaseQuickAdapter<StoreCouponBeans.DataBean, BaseViewHolder> {

    public SstoreGouponAdapter(){
        super(R.layout.item_sstore_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreCouponBeans.DataBean item) {
        if(helper.getAdapterPosition() == (getData().size() - 1)){
            helper.getView(R.id.rWhith).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.rWhith).setVisibility(View.GONE);
        }

        helper.setText(R.id.couponMoney, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.minMoney,"满"+BaseUtil.getNoZoon(item.getMin_money())+"可用");
    }
}
