package com.hongyuan.fitness.ui.shop.sadapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.PlatformCouponsBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class PlatformCouponAdapter extends BaseQuickAdapter<PlatformCouponsBeans.DataBean.ListBean, BaseViewHolder> {

    public PlatformCouponAdapter() {
        super(R.layout.item_platform_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlatformCouponsBeans.DataBean.ListBean item) {
        helper.setText(R.id.cMoney, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.cUseText,"满"+BaseUtil.getNoZoon(item.getMin_money())+"可用")
                .setText(R.id.cTitle,item.getCoupon_name())
                .setText(R.id.cDes,"适用于"+item.getCoupon_for_str());
        helper.setText(R.id.cData, "有效期至："+ item.getEnd_date());


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

        if(item.getIs_exp() == 1 || item.getIs_have() == 1){
            helper.getView(R.id.box).setAlpha(0.3f);
            helper.setBackgroundRes(R.id.box,R.drawable.shape_radius6_cccccc);
            helper.setVisible(R.id.goUse,false);
            helper.getView(R.id.goUse).setClickable(false);
        }else{
            helper.getView(R.id.box).setAlpha(1);
            helper.setBackgroundRes(R.id.box,R.drawable.theme_shape_radius6_ffffff_stroke_eeeeee);
            helper.addOnClickListener(R.id.goUse);
            helper.setVisible(R.id.goUse,true);
            helper.getView(R.id.goUse).setClickable(true);

        }


    }
}
