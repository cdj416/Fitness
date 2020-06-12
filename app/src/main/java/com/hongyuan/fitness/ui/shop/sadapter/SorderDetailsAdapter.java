package com.hongyuan.fitness.ui.shop.sadapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.custom_view.InputOrSlectView;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.ui.shop.sbeans.DtypeBeans;
import com.hongyuan.fitness.ui.shop.sbeans.PickUpAddress;
import com.hongyuan.fitness.ui.shop.sbeans.SorderCouponBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SorderDetailBeans.DataBean.BottomBean;
import com.hongyuan.fitness.ui.shop.sbeans.SorderDetailBeans.DataBean.ListBean;
import com.hongyuan.fitness.ui.shop.sbeans.SorderDetailBeans.DataBean.ListBean.GoodsListBean;
import com.hongyuan.fitness.ui.shop.sinterface.ChangeBottomAllPriceListener;
import com.hongyuan.fitness.ui.shop.sinterface.ViewRequestListener;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.HiddenAnimUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SorderDetailsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_THIRD = 2;

    //纯金额显示类型
    public static final int SHOW_MONEY = 0;
    //纯积分显示类型
    public static final int SHOW_POINT = 1;
    //金额加积分显示类型
    public static final int SHOW_MONEY_POINT = 2;

    //配送方式选择工具类
    private UnitBeanUtils dtypeUtils;
    private List<DtypeBeans> typeList;

    //用于获取自提数据
    private ViewRequestListener clickListener;
    //用于改变底部总价的监听
    private ChangeBottomAllPriceListener allPriceListener;
    //自提数据源
    private Map<String,PickUpAddress> addresMap;
    //红包数据源
    private Map<String,List<SorderCouponBeans.DataBean.ListBean>> couponMap;

    public SorderDetailsAdapter(List<MultiItemEntity> data, ViewRequestListener clickListener, ChangeBottomAllPriceListener allPriceListener){
        super(data);
        this.clickListener = clickListener;
        this.allPriceListener = allPriceListener;

        addItemType(TYPE_ONE, R.layout.item_order_details_title);
        addItemType(TYPE_TWO, R.layout.item_order_details_goods);
        addItemType(TYPE_THIRD, R.layout.item_order_details_footer);

        typeList = new ArrayList<>();
        typeList.add(new DtypeBeans("1","快递配送"));
        typeList.add(new DtypeBeans("2","上门自提"));
        dtypeUtils = new UnitBeanUtils<DtypeBeans>() {
            @Override
            public String unit(DtypeBeans o) {
                return o.unit;
            }

            @Override
            public String unit_cn(DtypeBeans o) {
                return o.unit_cn;
            }
        };

        addresMap = new HashMap<>();
        couponMap = new HashMap<>();

        //改变底部价格
        allPriceListener.changeAllprice(getAllPrice());
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_ONE:
                ListBean oneBean = (ListBean) item;
                helper.setText(R.id.storeName, oneBean.getStore_name());
                break;

            case TYPE_TWO:
                GoodsListBean twoBean = (GoodsListBean) item;
                RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
                Glide.with(mContext).load(twoBean.getG_img()).apply(options).into((RoundedImageView)helper.getView(R.id.goodImg));

                helper.setText(R.id.goodName,twoBean.getG_name())
                        .setText(R.id.goodPrice, BaseUtil.getNoZoon(twoBean.getGp_price()))
                        .setText(R.id.goodsPoint,String.valueOf(twoBean.getGp_point()))
                        .setText(R.id.goodNum,"x"+twoBean.getBuy_num());

                if(BaseUtil.isValue(twoBean.getSku_names())){
                    helper.setVisible(R.id.goodType,true).setText(R.id.goodType,twoBean.getSku_names());
                }else{
                    helper.setVisible(R.id.goodType,false);
                }

                if(twoBean.getShowType() == SHOW_MONEY){
                    helper.getView(R.id.pointBox).setVisibility(View.GONE);
                    helper.getView(R.id.addMark).setVisibility(View.GONE);
                    helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
                }else if(twoBean.getShowType() == SHOW_POINT){
                    helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
                    helper.getView(R.id.addMark).setVisibility(View.GONE);
                    helper.getView(R.id.priceBox).setVisibility(View.GONE);
                }else if (twoBean.getShowType() == SHOW_MONEY_POINT){
                    helper.getView(R.id.pointBox).setVisibility(View.VISIBLE);
                    helper.getView(R.id.addMark).setVisibility(View.VISIBLE);
                    helper.getView(R.id.priceBox).setVisibility(View.VISIBLE);
                }

                if(twoBean.getIs_valid() == 0){
                    helper.getView(R.id.invalidBox).setVisibility(View.VISIBLE);
                    helper.getView(R.id.goDetail).setAlpha(0.3f);
                    helper.getView(R.id.goodImg).setAlpha(0.3f);
                }else{
                    helper.getView(R.id.invalidBox).setVisibility(View.GONE);
                    helper.getView(R.id.goDetail).setAlpha(1f);
                    helper.getView(R.id.goodImg).setAlpha(1f);
                }

                break;

            case TYPE_THIRD:

                BottomBean thirdBean = (BottomBean)item;

                InputOrSlectView deliveryMethod = helper.getView(R.id.deliveryMethod);
                InputOrSlectView selfMention = helper.getView(R.id.selfMention);
                ((EditText)helper.getView(R.id.notes)).addTextChangedListener(getEtWatcher(thirdBean));

                HiddenAnimUtils animUtils = HiddenAnimUtils.newInstance(mContext,selfMention,null,44,0,false);
                if(thirdBean.getDeliveryType() == 1){
                    deliveryMethod.setRightText("快递："+thirdBean.getDelivery());
                    deliveryMethod.setOnClickListener(getClick(mContext,thirdBean,helper.getView(R.id.allPrice),selfMention,animUtils));
                    selfMention.setOnClickListener(getAddressClick(mContext,selfMention,thirdBean));
                }else{
                    deliveryMethod.setRightText("快递："+thirdBean.getDelivery(),false);
                }

                InputOrSlectView myCoupon = helper.getView(R.id.myCoupon);
                if(BaseUtil.isValue(thirdBean.getStoreCoupon())){
                    myCoupon.setVisibility(View.VISIBLE);
                    myCoupon.setRightText(thirdBean.getStoreCoupon());
                    myCoupon.setOnClickListener(getCoupon(myCoupon,helper.getView(R.id.allPrice),thirdBean));
                }else{
                    myCoupon.setVisibility(View.GONE);
                }

                if(thirdBean.getAllPoint() > 0){
                    helper.setText(R.id.pointNum,"+ "+thirdBean.getAllPoint()+"积分");
                    helper.getView(R.id.pointNum).setVisibility(View.VISIBLE);
                }else{
                    helper.getView(R.id.pointNum).setVisibility(View.GONE);
                }



                helper.setText(R.id.allNum,"共"+thirdBean.getAllNum()+"件，小计：");
                helper.setText(R.id.allPrice,"¥"+thirdBean.getUseAllprice());


                break;
        }
    }

    //红包的点击时间
    public View.OnClickListener getCoupon(InputOrSlectView myCoupon,TextView allPriceView,BottomBean item){
        return v -> {

            if(!couponMap.containsKey(item.getStoreId())){
                //去请求红包数据
                clickListener.listener(myCoupon,allPriceView,item);
            }else{
                showCoupon(myCoupon,allPriceView,item);
            }
        };
    }

    //红包弹框
    public void showCoupon(InputOrSlectView myCoupon,TextView allPriceView,BottomBean item){
        List<SorderCouponBeans.DataBean.ListBean> couponList = couponMap.get(item.getStoreId());
        CustomDialog.showSorderCoupons(mContext, couponList, (view, position, adapter) -> {
            myCoupon.setRightText(BaseUtil.getNoZoon(couponList.get(position).getCoupon_money()));

            //设置选中的红包金额
            item.setStoreCoupon(couponList.get(position).getCoupon_money());
            //设置选中红包的cmid
            item.setCmId(couponList.get(position).getCm_id());
            //显示红包选择后的结果
            allPriceView.setText("￥"+ getShowPrice(item.getDeliverWay() == 1 ,item));
        });
    }

    //配送方式的点击事件
    public View.OnClickListener getClick(Context mContext,BottomBean item,TextView allPriceView,InputOrSlectView selfMention,HiddenAnimUtils animUtils){
        return v -> {

            CustomDialog.scroller(mContext, dtypeUtils.getUnitList(typeList), "选择配送方式", (view, message) -> {
                InputOrSlectView typeDel = (InputOrSlectView)v;
                if("快递配送".equals(message)){
                    typeDel.setRightText("快递："+item.getDelivery());
                    animUtils.toggleHeight(false);

                    //设置快递方式
                    item.setDeliverWay(1);
                    //清楚自提地址id
                    item.setZtAddressId(0);
                    //有快递时使用快递费用价格
                    allPriceView.setText("￥"+getShowPrice(true,item));
                }else{
                    typeDel.setRightText(message);
                    animUtils.toggleHeight(true);

                    if(!addresMap.containsKey(item.getStoreId())){
                        //去请求自提地址
                        clickListener.listener(selfMention,selfMention,item);
                    }else{
                        //设置自提地址id
                        item.setZtAddressId(addresMap.get(item.getStoreId()).getData().getList().get(0).getZt_address_id());
                    }
                    //设置快递方式
                    item.setDeliverWay(2);
                    //无快递时使用的费用价格
                    allPriceView.setText("￥"+getShowPrice(false,item));
                }
            });
        };
    }

    //自提地点的点击事件
    public View.OnClickListener getAddressClick(Context mContext,InputOrSlectView selfMention,BottomBean item){
        return v -> {

            if(addresMap == null || addresMap.size() <= 0){
                return;
            }

            CustomDialog.showPickUpAddress(mContext, addresMap.get(item.getStoreId()).getData().getList(), (v1, position, adapter) -> {
                selfMention.setRightText(addresMap.get(item.getStoreId()).getData().getList().get(position).getPname()+" "
                        +addresMap.get(item.getStoreId()).getData().getList().get(position).getCname()+" "
                        +addresMap.get(item.getStoreId()).getData().getList().get(position).getDname()+" "
                        +addresMap.get(item.getStoreId()).getData().getList().get(position).getAddress());
                //设置自提地址id
                item.setZtAddressId(addresMap.get(item.getStoreId()).getData().getList().get(position).getZt_address_id());
            });
        };
    }

    //备注输入监听
    public TextWatcher getEtWatcher(BottomBean item){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setNote(s.toString());
            }
        };
    }

    /*
    * 设置自提数据
    * */
    public void setPickUpData(View view, String storeId, PickUpAddress upAddress){
        InputOrSlectView selfMention = (InputOrSlectView) view;
        if(upAddress.getData().getList() != null && upAddress.getData().getList().size() > 0){
            selfMention.setRightText(upAddress.getData().getList().get(0).getPname() + " "
                    + upAddress.getData().getList().get(0).getCname() + " "
                    + upAddress.getData().getList().get(0).getDname() + " "
                    + upAddress.getData().getList().get(0).getAddress());

        }

        addresMap.put(storeId,upAddress);
    }

    /*
    * 设置红包数据源
    * */
    public void setCouponData(InputOrSlectView view,TextView allPriceView,BottomBean item,List<SorderCouponBeans.DataBean.ListBean> couponList){
        if(couponList != null && couponList.size() > 0){
            couponMap.put(item.getStoreId(),couponList);

            //显示优惠卷弹框
            showCoupon(view,allPriceView,item);
        }
    }

    /*
    * 自提快递选择价格的计算
    * flag:true表示快递，false表示自提
    * */
    private String getShowPrice(boolean flag,BottomBean item){
        String showPrice;
        if(flag){
            if(BaseUtil.isValue(item.getStoreCoupon()) && Double.parseDouble(item.getStoreCoupon()) > 0){
                showPrice = BigDecimalUtils.sub(item.getAllPrice(),item.getStoreCoupon(),2);
            }else{
                showPrice = item.getAllPrice();
            }
            showPrice = BigDecimalUtils.add(showPrice,item.getDelivery(),2);
        }else{
            if(BaseUtil.isValue(item.getStoreCoupon()) && Double.parseDouble(item.getStoreCoupon()) > 0){
                showPrice = BigDecimalUtils.sub(item.getAllPrice(),item.getStoreCoupon(),2);
            }else{
                showPrice = item.getAllPrice();
            }
        }

        //从新设置下使用的总价
        item.setUseAllprice(showPrice);

        //改变底部价格
        allPriceListener.changeAllprice(getAllPrice());

        return BaseUtil.getNoZoon(showPrice);
    }

    /*
    * 计算总价格
    * */
    public String getAllPrice(){
        String showAllPrice = "0";
        for(MultiItemEntity itemEntity : getData()){
            if(itemEntity instanceof BottomBean){
                BottomBean bean = (BottomBean)itemEntity;
                showAllPrice = BigDecimalUtils.add(showAllPrice,bean.getUseAllprice(),2);
            }
        }

        return BaseUtil.getNoZoon(showAllPrice);
    }
}
