package com.hongyuan.fitness.ui.person.my_coupon.receive_coupon_list;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class ReceiveListCouponAdapter extends BaseQuickAdapter<CouponListBeans.DataBean.ListBean, BaseViewHolder> {

    public ReceiveListCouponAdapter() {
        super(R.layout.item_receive_list_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListBeans.DataBean.ListBean item) {
        helper.setText(R.id.cMoney, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.cUseText,"满"+BaseUtil.getNoZoon(item.getMin_money())+"可用")
                .setText(R.id.cTitle,item.getCoupon_name())
                .setText(R.id.cDes,"适用于"+item.getCoupon_for_str())
                .setText(R.id.cData, "有效期："+item.getStart_date().substring(0,11)+"-"+
                        item.getEnd_date().substring(0,11));

        if(BaseUtil.isValue(item.getOsl_name())){
            helper.setText(R.id.cName,"适用于"+item.getOsl_name());
        }else{
            if(item.getOs_names() != null && item.getOs_names().size() > 0){
                if(item.getOs_names().size() == 1){
                    helper.setText(R.id.cName,"适用于"+item.getOs_names().get(0));
                }else{
                    helper.setText(R.id.cName,"适用于"+item.getOs_names().get(0)+"等"+item.getOs_names().size()+"家门店可用");
                }
            }else{
                helper.setText(R.id.cName," ");
            }

        }

        if(item.isReceive()){
            helper.getView(R.id.cReceive).setClickable(false);
            helper.setBackgroundRes(R.id.cReceive,R.color.transparent);
            helper.setTextColor(R.id.cReceive,mContext.getResources().getColor(R.color.color_FFFFFF));
            helper.setText(R.id.cReceive,"已领取");
        }else{
            helper.getView(R.id.cReceive).setClickable(true);
            helper.setBackgroundRes(R.id.cReceive,R.drawable.shape_radius14_ffffffff);
            if(item.getCoupon_type() == 1){
                helper.setBackgroundRes(R.id.box,R.mipmap.blue_coupon_bg);
                helper.setTextColor(R.id.cReceive,mContext.getResources().getColor(R.color.color_FF6787F1));
            }else{
                helper.setBackgroundRes(R.id.box,R.mipmap.orange_coupon_bg);
                helper.setTextColor(R.id.cReceive,mContext.getResources().getColor(R.color.color_EF5B48));
            }
            helper.setText(R.id.cReceive,"立即领取");
            helper.addOnClickListener(R.id.cReceive);
        }

    }
}
