package com.hongyuan.fitness.ui.person.my_coupon.select_coupon;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.person.my_coupon.CouponListBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class SelectCouponAdapter extends BaseQuickAdapter<CouponListBeans.DataBean.ListBean, BaseViewHolder> {

    public SelectCouponAdapter() {
        super(R.layout.item_select_coupon);
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
        if(item.getCoupon_type() == 1){
            helper.setBackgroundRes(R.id.box,R.mipmap.blue_coupon_bg);
        }else{
            helper.setBackgroundRes(R.id.box,R.mipmap.orange_coupon_bg);
        }

        if(item.isSelect()){
            helper.setVisible(R.id.selectImg,true);
        }else{
            helper.setVisible(R.id.selectImg,false);
        }

        helper.addOnClickListener(R.id.box);
    }
}
