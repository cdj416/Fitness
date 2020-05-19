package com.hongyuan.fitness.ui.person.my_coupon;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.util.BaseUtil;

public class CouponAdapter extends BaseQuickAdapter<CouponListBeans.DataBean.ListBean, BaseViewHolder> {

    public CouponAdapter() {
        super(R.layout.item_my_coupon);
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

        if(item.getIs_exp() == 1){
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius6_cccccc);
            helper.setVisible(R.id.goUse,false);
            helper.getView(R.id.goUse).setClickable(false);
        }else{

            if(item.getIs_use() == 1){
                helper.setBackgroundRes(R.id.box,R.drawable.shape_radius6_cccccc);
                helper.setVisible(R.id.goUse,false);
                helper.getView(R.id.goUse).setClickable(false);
            }else{
                helper.setBackgroundRes(R.id.box,R.drawable.shape_gradient_v_ffef5b48_ffff8041);
                helper.setText(R.id.goUse,"去使用");
                helper.addOnClickListener(R.id.goUse);
                helper.setVisible(R.id.goUse,true);
                helper.getView(R.id.goUse).setClickable(true);
            }

        }


    }
}
