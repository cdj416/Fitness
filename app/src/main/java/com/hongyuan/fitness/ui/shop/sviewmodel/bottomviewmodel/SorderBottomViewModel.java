package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomSorderDetailsBinding;
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sbeans.AddressInfoBeans;
import com.hongyuan.fitness.ui.shop.sbeans.CreateOrderBeans;
import com.hongyuan.fitness.ui.shop.sviewmodel.SorderDetailViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

public class SorderBottomViewModel extends CustomViewModel {

    private ActivityBottomSorderDetailsBinding binding;

    private SorderDetailViewModel viewModel;

    //查询的积分数据
    private PointBean pointBean;

    //积分兑换，商品积分
    private int mPoint;

    public SorderBottomViewModel(CustomActivity mActivity, SorderDetailViewModel viewModel, ActivityBottomSorderDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        this.viewModel = viewModel;
        initView();
        getPoint();
    }

    @Override
    protected void initView() {
        viewModel.setBottomViewModel(this);

        binding.submit.setOnClickListener(v -> {

            //去生成订单
            lazyLoad();

        });
    }

    /*
    * 显示总价
    * */
    public void changeAllPrice(String allPrice){

        if(Integer.parseInt(allPrice) > 0){
            binding.allPrice.setText(allPrice);
        }else{
            mPoint = viewModel.getInfoData().getList().get(0).getAll_point();
            binding.pointNum.setVisibility(View.VISIBLE);
            binding.pointNum.setText("+积分："+mPoint);
            binding.submit.setText("立即兑换");
        }


    }

    /*
    * 查询积分
    * */
    private void getPoint(){
        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    @Override
    protected void lazyLoad() {
        AddressInfoBeans.DataBean addressBeans = viewModel.getAddressBeans();

        mActivity.showLoading();
        clearParams().setParams("order_json",viewModel.getJsonData())
                .setParams("pid",String.valueOf(addressBeans.getPid()))
                .setParams("cid",String.valueOf(addressBeans.getCid()))
                .setParams("did",String.valueOf(addressBeans.getDid()))
                .setParams("pname",addressBeans.getProvince())
                .setParams("cname",addressBeans.getCity())
                .setParams("dname",addressBeans.getDistrict())
                .setParams("address",addressBeans.getAddress())
                .setParams("consignee",addressBeans.getName())
                .setParams("deliver_mobile",addressBeans.getTel());

        Controller.myRequest(Constants.ADD_GOODS_ORDER_NEW,Controller.TYPE_POST,getParams(), CreateOrderBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();

        if(data instanceof CreateOrderBeans){
            CreateOrderBeans.DataBean bean = ((CreateOrderBeans)data).getData();

            PayDataBean payDataBean = new PayDataBean();
            if(BaseUtil.isValue(bean.getO_ids().getMoney_o_ids())){
                payDataBean.setO_id(bean.getO_ids().getMoney_o_ids());
                payDataBean.setShowPoint("");
                payDataBean.setShowPrice(binding.allPrice.getText().toString());
                payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));
            }else if(BaseUtil.isValue(bean.getO_ids().getPoint_o_ids())){
                payDataBean.setO_id(bean.getO_ids().getPoint_o_ids());
                payDataBean.setShowPoint(""+mPoint);
                payDataBean.setShowPrice(binding.allPrice.getText().toString());
                payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));
            }else{
                CustomDialog.showMessage(mActivity,"该商品已下架！");
            }


            Bundle bundle = new Bundle();
            bundle.putSerializable("payDataBean",payDataBean);
            bundle.putSerializable("successBeans",getSuccessBeans());
            startActivity(GoodsPayActivity.class,bundle);
        }

        if(data instanceof PointBean){
            pointBean = (PointBean)data;
        }
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(){
        V3SuccessBeans beans = new V3SuccessBeans();

        beans.setTitleText("订单");
        beans.setShowText("购买成功");
        beans.setBtn2Text("完成");

        return beans;
    }
}
