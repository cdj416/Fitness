package com.hongyuan.fitness.ui.shop.sadapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SccouponBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class SccouponsAdapter extends BaseQuickAdapter<SccouponBeans.DataBean.ListBean, BaseViewHolder> {

    public SccouponsAdapter(){
        super(R.layout.item_collect_coupons);
    }

    @Override
    protected void convert(BaseViewHolder helper, SccouponBeans.DataBean.ListBean item) {

        helper.setText(R.id.money, BaseUtil.getNoZoon(item.getCoupon_money()))
                .setText(R.id.minMoney,"满"+BaseUtil.getNoZoon(item.getMin_money())+"使用")
                .setText(R.id.useStores,"限 "+item.getStore_name()+" 店铺使用");

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);

        if(item.getGoods_list() != null && item.getGoods_list().size() > 0){
            if(item.getGoods_list().size() == 1){
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                helper.setText(R.id.goodName1,item.getGoods_list().get(0).getG_name()).setText(R.id.goodPrice1,BaseUtil.getNoZoon(item.getGoods_list().get(0).getG_price()));
                helper.setVisible(R.id.goodBox1,true).setVisible(R.id.goodBox2,false).setVisible(R.id.goodBox3,false);

                helper.addOnClickListener(R.id.goodBox1);
            }

            if(item.getGoods_list().size() == 2){
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                helper.setText(R.id.goodName1,item.getGoods_list().get(0).getG_name()).setText(R.id.goodPrice1,BaseUtil.getNoZoon(item.getGoods_list().get(0).getG_price()));

                Glide.with(mContext).load(item.getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                helper.setText(R.id.goodName2,item.getGoods_list().get(1).getG_name()).setText(R.id.goodPrice2,BaseUtil.getNoZoon(item.getGoods_list().get(1).getG_price()));
                helper.setVisible(R.id.goodBox1,true).setVisible(R.id.goodBox2,true).setVisible(R.id.goodBox3,false);

                helper.addOnClickListener(R.id.goodBox1).addOnClickListener(R.id.goodBox2);
            }
            if(item.getGoods_list().size() >= 2){
                Glide.with(mContext).load(item.getGoods_list().get(0).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img1));
                helper.setText(R.id.goodName1,item.getGoods_list().get(0).getG_name()).setText(R.id.goodPrice1,BaseUtil.getNoZoon(item.getGoods_list().get(0).getG_price()));

                Glide.with(mContext).load(item.getGoods_list().get(1).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img2));
                helper.setText(R.id.goodName2,item.getGoods_list().get(1).getG_name()).setText(R.id.goodPrice2,BaseUtil.getNoZoon(item.getGoods_list().get(1).getG_price()));

                Glide.with(mContext).load(item.getGoods_list().get(2).getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.img3));
                helper.setText(R.id.goodName3,item.getGoods_list().get(2).getG_name()).setText(R.id.goodPrice3,BaseUtil.getNoZoon(item.getGoods_list().get(2).getG_price()));

                helper.setVisible(R.id.goodBox1,true).setVisible(R.id.goodBox2,true).setVisible(R.id.goodBox3,true);
                helper.addOnClickListener(R.id.goodBox1).addOnClickListener(R.id.goodBox2).addOnClickListener(R.id.goodBox3);
            }
        }else{
            helper.setVisible(R.id.goodBox1,false).setVisible(R.id.goodBox2,false).setVisible(R.id.goodBox3,false);

        }

        if(item.getIs_lq() == 1){
            helper.setText(R.id.recCoupon,"已领取").setBackgroundRes(R.id.recCoupon,R.drawable.shape_radius24_cccccc);
            helper.getView(R.id.recCoupon).setClickable(false);
        }else{
            helper.setText(R.id.recCoupon,"立即领取").setBackgroundRes(R.id.recCoupon,R.drawable.shape_radius16_ef5b48);
            helper.getView(R.id.recCoupon).setClickable(true);
        }

    }
}
