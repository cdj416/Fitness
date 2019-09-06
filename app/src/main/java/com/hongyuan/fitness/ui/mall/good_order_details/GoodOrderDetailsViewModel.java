package com.hongyuan.fitness.ui.mall.good_order_details;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityGoodOrderDetailBinding;
import com.hongyuan.fitness.ui.mall.good_details.GoodDetailsBean;
import com.hongyuan.fitness.ui.mall.good_details.GoodSelectSkuBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GoodOrderDetailsViewModel extends CustomViewModel {

    private ActivityGoodOrderDetailBinding binding;
    //商品数据
    private GoodDetailsBean.DataBean.InfoBean infoBean;
    //请求数据
    public CreateOrderDetailsBean paramsBean;
    //查询的积分数据
    private PointBean pointBean;

    public GoodOrderDetailsViewModel(CustomActivity mActivity, ActivityGoodOrderDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        infoBean = (GoodDetailsBean.DataBean.InfoBean)getBundle().getSerializable("InfoBean");
        paramsBean = (CreateOrderDetailsBean)getBundle().getSerializable("paramsBean");

        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_testbaner3).error(R.mipmap.a_testbaner3);
        Glide.with(mActivity).load(infoBean.getG_img()).apply(options).into(binding.goodImg);

        binding.goodName.setText(infoBean.getG_name());
        binding.norm.setText(paramsBean.getNorm());
        binding.goodNum.setText("x"+paramsBean.getOp_num());
        binding.unitPrice.setText(BaseUtil.getNoZoon(infoBean.getG_price()));
        binding.buyNum.setText(paramsBean.getOp_num());
        binding.buyPoint.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(String.valueOf(infoBean.getG_point()),paramsBean.getOp_num(),2)));
        binding.buyAllPrice.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(infoBean.getG_price(),paramsBean.getOp_num(),2)));
        if(Double.valueOf(infoBean.getG_price()) > 0){
            binding.goodPrice.setText(BaseUtil.getNoZoon(infoBean.getG_price()));
            binding.allPrice.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(infoBean.getG_price(),paramsBean.getOp_num(),2)));
        }else{
            binding.goodPrice.setVisibility(View.GONE);
            binding.allPriceBox.setVisibility(View.GONE);
        }
        if(infoBean.getG_point() > 0){
            binding.goodPoint.setText(BaseUtil.getNoZoon(infoBean.getG_point()));
            binding.allPoint.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(String.valueOf(infoBean.getG_point()),paramsBean.getOp_num(),2)));
        }else{
            binding.goodPoint.setVisibility(View.GONE);
            binding.allPointBox.setVisibility(View.GONE);
        }
        if(paramsBean.getOp_quhuo_osid() != null){
            binding.quhuoStoreName.setText(paramsBean.getQuhuostoreName());
        }else{
            binding.quhuoStoreName.setText("请选择");
        }
    }

    //选择门店
    public BindingCommand goStore = new BindingCommand(() -> {
        startActivity(MoreStoreActivity.class,null);
    });

    //选择门店
    public BindingCommand goPay = new BindingCommand(() -> {
        if(paramsBean.getOp_quhuo_osid() != null){
            creatOrder();
        }else{
            onError(0,"请选择取货门店");
        }
    });

    /*
    * 下单
    * */
    private void creatOrder(){
        clearParams().setParams("gp_id",paramsBean.getGp_id()).setParams("op_num",paramsBean.getOp_num())
                .setParams("store_id",paramsBean.getStore_id()).setParams("op_quhuo_osid",paramsBean.getOp_quhuo_osid());
        Controller.myRequest(Constants.ADD_GOODS_ORDER,Controller.TYPE_POST,getParams(), SubmitOrderBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PointBean){
            pointBean = (PointBean)data;
            binding.minePoint.setText(BaseUtil.getNoZoon(pointBean.getData().getPoint()));
        }
        if(data instanceof SubmitOrderBean){
            SubmitOrderBean submitOrderBean = (SubmitOrderBean)data;
            PayDataBean payDataBean = new PayDataBean();
            payDataBean.setO_id(submitOrderBean.getData().getO_id());
            payDataBean.setShowPoint(binding.allPoint.getText().toString());
            payDataBean.setShowPrice(binding.allPrice.getText().toString());
            payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));
            Bundle bundle = new Bundle();
            bundle.putSerializable("payDataBean",payDataBean);
            startActivity(GoodsPayActivity.class,bundle);
        }
    }
}
