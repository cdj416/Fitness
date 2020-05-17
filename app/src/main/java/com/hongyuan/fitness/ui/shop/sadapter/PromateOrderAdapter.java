package com.hongyuan.fitness.ui.shop.sadapter;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.ui.shop.sbeans.PromateOrderBeans.DataBean.ListBean;
import com.hongyuan.fitness.ui.shop.sbeans.PromateOrderBeans.DataBean.ListBean.GoodsListBean;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PromateOrderAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public PromateOrderAdapter(List<MultiItemEntity> data){
        super(data);
        addItemType(TYPE_ONE, R.layout.item_neworders_title);
        addItemType(TYPE_TWO, R.layout.item_promate_orders);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                ListBean oneBean = (ListBean) item;
                helper.setText(R.id.storeName, oneBean.getStore_name());

                if(oneBean.getO_state() == SnewOrderBeans.DataBean.BottomBean.STATU_PAY){
                    helper.setText(R.id.status,"待付款").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == SnewOrderBeans.DataBean.BottomBean.STATU_DELIVERY){
                    helper.setText(R.id.status,"待发货").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == SnewOrderBeans.DataBean.BottomBean.STATU_SHIPPED){
                    helper.setText(R.id.status,"待收货").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == SnewOrderBeans.DataBean.BottomBean.STATU_BE_EVALUATED){
                    helper.setText(R.id.status,"待评价").setTextColor(R.id.status,mContext.getResources().getColor(R.color.green));
                }
                break;
            case TYPE_TWO:
                GoodsListBean twoBean = (GoodsListBean) item;
                RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
                Glide.with(mContext).load(twoBean.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

                helper.setText(R.id.goodName,twoBean.getG_name())
                        .setText(R.id.goodPrice, BaseUtil.getNoZoon(twoBean.getGp_price()));

                if(BaseUtil.isValue(twoBean.getSku_names())){
                    helper.setVisible(R.id.goodType,true).setText(R.id.goodType,twoBean.getSku_names());
                }else{
                    helper.setVisible(R.id.goodType,false);
                }
                break;
        }
    }


}
