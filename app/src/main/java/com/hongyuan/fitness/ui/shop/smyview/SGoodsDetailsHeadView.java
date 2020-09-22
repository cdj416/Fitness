package com.hongyuan.fitness.ui.shop.smyview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.RetrofitListener;
import com.hongyuan.fitness.ui.shop.sbeans.ScouponsBean;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.List;

public class SGoodsDetailsHeadView extends LinearLayout implements RetrofitListener {

    private CustomFragment mFragment;

    private RelativeLayout parameterBox,couponBox;
    private LinearLayout specificationBox,collectionBox,pointBox;
    private ImageView collectionImg;
    private RoundedImageView normImg;

    private TextView tvPrice,tvIncome,saleNum,couponName,goodsName,tvCollection,tvGaddress,deliverFee
            ,cityName,zhiMark,normNum,secondName,ziTiTv,goodPoint,tvOldPrice;

    //商品详情数据
    private SgoodsDetailBeans.DataBean.InfoBean infoBean;
    //红包数据
    private List<ScouponsBean.DataBean> couponsList;
    //优惠券弹框返回来的适配器，用于刷新显示数据
    private BaseQuickAdapter couponAdapter;
    //优惠券领取的下标
    private int couponPosition;

    public SGoodsDetailsHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayoutView();
    }

    public void initLayoutView() {
        View view = View.inflate(getContext(), R.layout.view_s_detail_header, this);

        couponBox = view.findViewById(R.id.couponBox);
        parameterBox = view.findViewById(R.id.parameterBox);
        specificationBox = view.findViewById(R.id.specificationBox);

        tvPrice = view.findViewById(R.id.tvPrice);
        tvOldPrice = view.findViewById(R.id.tvOldPrice);
        tvIncome = view.findViewById(R.id.tvIncome);
        saleNum = view.findViewById(R.id.saleNum);
        couponName = view.findViewById(R.id.couponName);
        goodsName = view.findViewById(R.id.goodsName);
        tvCollection = view.findViewById(R.id.tvCollection);
        tvGaddress = view.findViewById(R.id.tvGaddress);
        deliverFee = view.findViewById(R.id.deliverFee);
        cityName = view.findViewById(R.id.cityName);
        ziTiTv = view.findViewById(R.id.ziTiTv);
        zhiMark = view.findViewById(R.id.zhiMark);
        collectionImg = view.findViewById(R.id.collectionImg);
        collectionBox = view.findViewById(R.id.collectionBox);
        normImg = view.findViewById(R.id.normImg);
        normNum = view.findViewById(R.id.normNum);
        secondName = view.findViewById(R.id.secondName);
        pointBox = view.findViewById(R.id.pointBox);
        goodPoint = view.findViewById(R.id.goodPoint);

        tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线


        //优惠卷弹框
        couponBox.setOnClickListener(v -> {
            if(couponsList == null || couponsList.size() == 0){
                CustomDialog.showMessage(getContext(),"暂无优惠券！");
            }else{
                CustomDialog.showSGDCoupon(getContext(), couponsList, (v1, position, adapter) -> {
                    couponAdapter = adapter;
                    this.couponPosition = position;
                    receiveCoupon(String.valueOf(couponsList.get(position).getCoupon_id()));
                });
            }
        });
        //参数弹框
        parameterBox.setOnClickListener(v -> {
            CustomDialog.showGoodsParameter(getContext());
        });
        //规格弹框
        specificationBox.setOnClickListener(v -> {
            CustomDialog.showGoodsSpecification(getContext(),infoBean,mFragment,null);
        });
        //收藏商品
        collectionBox.setOnClickListener(v -> {
            addCollection(String.valueOf(infoBean.getG_id()));
        });
    }

    /*
    * 设置显示数据
    * */
    public void showData(SgoodsDetailBeans.DataBean.InfoBean infoBean,CustomFragment mFragment){
        this.mFragment = mFragment;
        this.infoBean = infoBean;

        tvPrice.setText(BaseUtil.getNoZoon(infoBean.getG_price()));
        tvOldPrice.setText("￥"+infoBean.getG_marcket_price());
        saleNum.setText("销量"+infoBean.getG_sale_num());
        goodsName.setText(infoBean.getG_name());
        tvGaddress.setText(infoBean.getG_address());
        cityName.setText(infoBean.getCity_name());
        secondName.setText(infoBean.getG_second_name());

        if(infoBean.getDeliver_fee() > 0){
            deliverFee.setText(BaseUtil.getNoZoon(infoBean.getDeliver_fee()));
        }
        if(Double.parseDouble(infoBean.getG_income()) > 0){
            tvIncome.setVisibility(VISIBLE);
            tvIncome.setText("收益"+BaseUtil.getNoZoon(infoBean.getG_income()));
        }

        if(BaseUtil.isValue(infoBean.getCity_name())){
            zhiMark.setVisibility(VISIBLE);
            cityName.setText(infoBean.getCity_name());
        }else{
            cityName.setVisibility(GONE);
            zhiMark.setVisibility(GONE);
        }

        if(infoBean.getHave_zt() == 1){
            ziTiTv.setVisibility(VISIBLE);
        }else{
            ziTiTv.setVisibility(GONE);
        }

        if(infoBean.getIs_collection() == 1){
            collectionImg.setImageResource(R.mipmap.orange_collection_mark);
        }else{
            collectionImg.setImageResource(R.mipmap.gray_collection_img);
        }

        if(infoBean.getSku() != null && infoBean.getSku().size() > 0){
            Glide.with(getContext()).load(infoBean.getG_img()).transition(DrawableTransitionOptions.withCrossFade()).into(normImg);
            normNum.setText("共"+infoBean.getSku().size()+"种规格可选");
        }

        if(infoBean.getG_point() > 0){
            pointBox.setVisibility(VISIBLE);
            goodPoint.setText(String.valueOf(infoBean.getG_point()));
        }else{
            pointBox.setVisibility(GONE);
        }
    }

    /*
    * 设置需要显示的红包数据
    * */
    public void showCoupons(List<ScouponsBean.DataBean> mList){
        this.couponsList = mList;
        if(mList != null && mList.size() > 0){
            couponBox.setVisibility(VISIBLE);
            couponName.setText(mList.get(0).getCoupon_name());
        }else{
            couponBox.setVisibility(GONE);
        }
    }

    /*********************************************数据请求变动************************************/

    /*
    * 领取优惠券
    * */
    private void receiveCoupon(String coupon_id){
        mFragment.clearParams().setParams("coupon_id",coupon_id);
        Controller.myRequest(ConstantsCode.GET_COUPON,Constants.GET_COUPON,Controller.TYPE_POST,mFragment.getParams(), BaseBean.class,this);
    }

    /*
    * 收藏商品
    * */
    private void addCollection(String id){
        mFragment.clearParams().setParams("collection_code","product");
        if(infoBean.getIs_collection() == 1){
            mFragment.setParams("out_id",id);
            Controller.myRequest(ConstantsCode.DEL_COLLECTION,Constants.DEL_COLLECTION,Controller.TYPE_POST,mFragment.getParams(), BaseBean.class,this);
        }else{
            mFragment.setParams("id",id);
            Controller.myRequest(ConstantsCode.ADD_COLLECTION,Constants.ADD_COLLECTION,Controller.TYPE_POST,mFragment.getParams(), BaseBean.class,this);
        }

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.GET_COUPON){
            couponsList.remove(couponPosition);
            couponAdapter.setNewData(couponsList);
            mFragment.showSuccess("领取成功！");
        }
        if(code == ConstantsCode.ADD_COLLECTION){
            mFragment.showSuccess("收藏成功！");
            collectionImg.setImageResource(R.mipmap.orange_collection_mark);
            infoBean.setIs_collection(1);
        }
        if(code == ConstantsCode.DEL_COLLECTION){
            mFragment.showSuccess("已取消收藏！");
            collectionImg.setImageResource(R.mipmap.gray_collection_img);
            infoBean.setIs_collection(0);
        }
    }

    @Override
    public void onError(int error_code, String description) {
        LemonBubble.showError(getContext(), description, 2000);
    }

    @Override
    public void closeRefresh() {

    }
}
