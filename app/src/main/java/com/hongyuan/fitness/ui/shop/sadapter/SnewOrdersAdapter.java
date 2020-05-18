package com.hongyuan.fitness.ui.shop.sadapter;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans.DataBean.ListBean;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans.DataBean.ListBean.GoodsListBean;
import com.hongyuan.fitness.ui.shop.sbeans.SnewOrderBeans.DataBean.BottomBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SnewOrdersAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_THIRD = 2;

    public SnewOrdersAdapter(List<MultiItemEntity> data){
        super(data);
        addItemType(TYPE_ONE, R.layout.item_neworders_title);
        addItemType(TYPE_TWO, R.layout.item_neworders_goods);
        addItemType(TYPE_THIRD, R.layout.item_neworders_bottom);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                ListBean oneBean = (ListBean) item;
                helper.setText(R.id.storeName, oneBean.getStore_name());

                if(oneBean.getO_state() == BottomBean.STATU_PAY){
                    helper.setText(R.id.status,"待付款").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == BottomBean.STATU_DELIVERY){
                    helper.setText(R.id.status,"待发货").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == BottomBean.STATU_SHIPPED){
                    helper.setText(R.id.status,"待收货").setTextColor(R.id.status,mContext.getResources().getColor(R.color.color_EF5B48));
                }else if(oneBean.getO_state() == BottomBean.STATU_BE_EVALUATED){
                    helper.setText(R.id.status,"待评价").setTextColor(R.id.status,mContext.getResources().getColor(R.color.green));
                }

                helper.addOnClickListener(R.id.titleBox);
                break;
            case TYPE_TWO:
                GoodsListBean twoBean = (GoodsListBean) item;
                RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
                Glide.with(mContext).load(twoBean.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

                helper.setText(R.id.goodName,twoBean.getG_name())
                        .setText(R.id.goodPrice, BaseUtil.getNoZoon(twoBean.getGp_price()))
                        .setText(R.id.goodNum,"x"+twoBean.getBuy_num());

                if(BaseUtil.isValue(twoBean.getSku_names())){
                    helper.setVisible(R.id.goodType,true).setText(R.id.goodType,twoBean.getSku_names());
                }else{
                    helper.setVisible(R.id.goodType,false);
                }

                helper.addOnClickListener(R.id.goodsBox);
                break;
            case TYPE_THIRD:
                BottomBean bottomBean = (BottomBean)item;

                helper.setText(R.id.goodNum,String.valueOf(bottomBean.getBuyNum()));
                helper.setText(R.id.goodAllPrice,BaseUtil.getNoZoon(bottomBean.getAllPrice()));

                if(bottomBean.getStatus() == BottomBean.STATU_PAY){
                    helper.getView(R.id.sPayBox).setVisibility(View.VISIBLE);
                    helper.getView(R.id.dDelivery).setVisibility(View.GONE);
                    helper.getView(R.id.collectionBox).setVisibility(View.GONE);
                    helper.getView(R.id.beEvaluatedBox).setVisibility(View.GONE);

                    helper.addOnClickListener(R.id.cancelOrder);
                    helper.addOnClickListener(R.id.goPay);
                }

                if(bottomBean.getStatus() == BottomBean.STATU_DELIVERY){
                    helper.getView(R.id.sPayBox).setVisibility(View.GONE);
                    helper.getView(R.id.dDelivery).setVisibility(View.VISIBLE);
                    helper.getView(R.id.collectionBox).setVisibility(View.GONE);
                    helper.getView(R.id.beEvaluatedBox).setVisibility(View.GONE);

                    helper.addOnClickListener(R.id.dDelivery);
                    helper.addOnClickListener(R.id.submitGoods);
                }

                if(bottomBean.getStatus() == BottomBean.STATU_SHIPPED){
                    helper.getView(R.id.sPayBox).setVisibility(View.GONE);
                    helper.getView(R.id.dDelivery).setVisibility(View.GONE);
                    helper.getView(R.id.collectionBox).setVisibility(View.VISIBLE);
                    helper.getView(R.id.beEvaluatedBox).setVisibility(View.GONE);

                    helper.addOnClickListener(R.id.lookCollection);
                    helper.addOnClickListener(R.id.submitGoods);
                }

                if(bottomBean.getStatus() == BottomBean.STATU_BE_EVALUATED){
                    helper.getView(R.id.sPayBox).setVisibility(View.GONE);
                    helper.getView(R.id.dDelivery).setVisibility(View.GONE);
                    helper.getView(R.id.collectionBox).setVisibility(View.GONE);
                    helper.getView(R.id.beEvaluatedBox).setVisibility(View.VISIBLE);

                    helper.addOnClickListener(R.id.goAginBugy);
                    helper.addOnClickListener(R.id.goEvaluate);
                }
                break;
        }
    }



    /*helper.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = helper.getAdapterPosition();
            if (oneBean.isExpanded()) {
                //收起
                collapse(pos);
            } else {
                //打开
                expand(pos);
            }
        }
    });*/

}