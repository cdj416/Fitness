package com.hongyuan.fitness.ui.person.my_coupon.store_receive_coupon;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class StoreReceiveCouponAdapter extends BaseQuickAdapter<CouponListBeans.DataBean.ListBean, BaseViewHolder> {

    public StoreReceiveCouponAdapter() {
        super(R.layout.item_store_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListBeans.DataBean.ListBean item) {
        helper.setText(R.id.money, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.moneyText,"满"+item.getMin_money()+"可用")
                .setText(R.id.titleName,item.getCoupon_name())
                .setText(R.id.des,"适用于"+item.getCoupon_for_str())
                .setText(R.id.showTime, item.getStart_date().substring(0,11)+"-"+
                        item.getEnd_date().substring(0,11));

        if(item.isReceive()){
            helper.setText(R.id.receive,"已领取")
                    .setTextColor(R.id.receive,mContext.getResources().getColor(R.color.color_FFFFFF))
                    .setBackgroundColor(R.id.receive,mContext.getResources().getColor(R.color.transparent));
            helper.getView(R.id.receive).setClickable(false);
        }else{
            helper.setText(R.id.receive,"立即领取")
                    .setTextColor(R.id.receive,mContext.getResources().getColor(R.color.color_EF5B48))
                    .setBackgroundRes(R.id.receive,R.drawable.shape_radius14_ffffffff);
            helper.getView(R.id.receive).setClickable(true);
            helper.addOnClickListener(R.id.receive);
        }

    }
}
