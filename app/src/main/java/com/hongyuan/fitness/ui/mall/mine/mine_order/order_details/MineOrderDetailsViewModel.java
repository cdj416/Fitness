package com.hongyuan.fitness.ui.mall.mine.mine_order.order_details;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMineOrderDetailBinding;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sactivity.CustomServerActivity;
import com.hongyuan.fitness.ui.shop.sbeans.PointBean;
import com.hongyuan.fitness.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class MineOrderDetailsViewModel extends CustomViewModel {

    private ActivityMineOrderDetailBinding binding;
    private MineOrderDetailsBeans.DataBean.InfoBean detailsBeans;
    private PointBean pointBean;

    public MineOrderDetailsViewModel(CustomActivity mActivity , ActivityMineOrderDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        binding.goCustomServer.setOnClickListener(v -> {
            startActivity(CustomServerActivity.class,null);
        });
    }

    //取消订单
    public BindingCommand cancelOrder = new BindingCommand(() -> getCancelOrder());
    //去支付或者确认收货
    public BindingCommand payOrSubmit = new BindingCommand(() -> {
        try {
            PayDataBean payDataBean = new PayDataBean();
            payDataBean.setO_id(String.valueOf(detailsBeans.getO_id()));
            payDataBean.setShowPoint(String.valueOf(detailsBeans.getO_point()));
            payDataBean.setShowPrice(detailsBeans.getO_money());
            payDataBean.setLavePoint(String.valueOf(pointBean.getData().getPoint()));
            Bundle bundle = new Bundle();
            bundle.putSerializable("payDataBean",payDataBean);
            bundle.putSerializable("successBeans",getSuccessBeans());
            startActivity(GoodsPayActivity.class,bundle);
        }catch (Exception e){
            e.printStackTrace();
        }
    });

    @Override
    protected void lazyLoad() {
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(Constants.GET_ORDER_INFO,Controller.TYPE_POST,getParams(), MineOrderDetailsBeans.class,this);

        Controller.myRequest(Constants.GET_MEMBER_POINT,Controller.TYPE_POST,getParams(), PointBean.class,this);
    }

    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mActivity).load(detailsBeans.getO_img()).apply(options).into(binding.goodImg);

        binding.goodName.setText(detailsBeans.getO_name());
        binding.norm.setText(getNormStr(detailsBeans.getSku()));
        binding.goodNum.setText("x"+detailsBeans.getO_num());
        binding.unitPrice.setText(BaseUtil.getNoZoon(detailsBeans.getO_price()));
        binding.buyNum.setText(String.valueOf(detailsBeans.getO_num()));
        binding.buyPoint.setText(BaseUtil.getNoZoon(detailsBeans.getO_point()));

        if(BaseUtil.isValue(detailsBeans.getAdd_date())){
            binding.orderAddTime.setText("创建时间："+detailsBeans.getAdd_date());
            binding.orderAddTime.setVisibility(View.VISIBLE);
        }
        if(BaseUtil.isValue(detailsBeans.getO_sn())){
            binding.orderNum.setText("订单编号："+detailsBeans.getO_sn());
            binding.orderNum.setVisibility(View.VISIBLE);
        }
        if(BaseUtil.isValue(detailsBeans.getPay_date())){
            binding.orderPayTime.setText("付款时间："+detailsBeans.getPay_date());
            binding.orderPayTime.setVisibility(View.VISIBLE);
        }
        if(BaseUtil.isValue(detailsBeans.getTihuo_code())){
            binding.pickupCode.setText("提货码："+detailsBeans.getTihuo_code());
            binding.pickupCode.setVisibility(View.VISIBLE);
        }
        if(BaseUtil.isValue(detailsBeans.getOs_name())){
            binding.pickUpAddress.setText("取货门店："+detailsBeans.getOs_name());
            binding.pickUpAddress.setVisibility(View.VISIBLE);
        }

        if(BaseUtil.isValue(detailsBeans.getO_price()) && Double.valueOf(detailsBeans.getO_price()) > 0){
            binding.goodPrice.setText(BaseUtil.getNoZoon(detailsBeans.getO_price()));
            binding.buyAllPrice.setText("¥"+BaseUtil.getNoZoon(detailsBeans.getO_money()));
        }else{
            binding.goodPrice.setVisibility(View.GONE);
            binding.buyAllPrice.setText(BaseUtil.getNoZoon(detailsBeans.getO_point())+"积分");
        }
        if(BaseUtil.isValue(detailsBeans.getOp_point()) && detailsBeans.getOp_point() > 0){
            binding.goodPoint.setText(BaseUtil.getNoZoon(detailsBeans.getOp_point()));
        }else{
            binding.goodPoint.setVisibility(View.GONE);
        }

        if(detailsBeans.getO_pay_state() == 2){
            binding.cancelOrder.setVisibility(View.VISIBLE);
            binding.payOrSubmit.setText("去支付");
        }else if(detailsBeans.getO_pay_state() == 1){
            binding.bottomBox.setVisibility(View.GONE);
            binding.payOrSubmit.setText("确认收货");
        }
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(){
        V3SuccessBeans beans = new V3SuccessBeans();

        if("o_card".equals(detailsBeans.getO_type_code())){
            beans.setType(V3SuccessBeans.TYPE.BUYCARD);
            beans.setTitleText("订单完成");
            beans.setShowText("购买成功");
            beans.setBtn1Text("跳过");
            beans.setBtn2Text("人脸识别录入");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailsBeans.getO_name());
            itemConten.setItemTitle("会籍卡名称:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("￥"+ BaseUtil.getNoZoon(detailsBeans.getO_price()));
            itemConten.setItemTitle("价格:");
            list.add(itemConten);

            if(BaseUtil.isValue(detailsBeans.getO_coupon_money()) && Double.valueOf(detailsBeans.getO_coupon_money()) > 0){
                itemConten = new V3SuccessBeans.ItemConten();
                itemConten.setContent("-¥"+BaseUtil.getNoZoon(detailsBeans.getO_coupon_money()));
                itemConten.setItemTitle("优惠:");
                list.add(itemConten);
            }
            beans.setItemContens(list);
        }

        if("o_pric".equals(detailsBeans.getO_type_code())){
            beans.setType(V3SuccessBeans.TYPE.PRIVITECLASS);
            beans.setTitleText("订单");
            beans.setShowText("购买成功");
            beans.setBtn2Text("完成");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailsBeans.getO_name()+" / 一对一私教课");
            itemConten.setItemTitle("课程类型:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+ BaseUtil.getNoZoon(detailsBeans.getO_price())+"/节");
            itemConten.setItemTitle("单价:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailsBeans.getO_num()+"节");
            itemConten.setItemTitle("数量:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+BaseUtil.getNoZoon(detailsBeans.getO_money()));
            itemConten.setItemTitle("总价:");
            list.add(itemConten);

            if(BaseUtil.isValue(detailsBeans.getO_coupon_money()) && Double.valueOf(detailsBeans.getO_coupon_money()) > 0){
                itemConten = new V3SuccessBeans.ItemConten();
                itemConten.setContent("-¥"+BaseUtil.getNoZoon(detailsBeans.getO_coupon_money()));
                itemConten.setItemTitle("优惠:");
                list.add(itemConten);
            }

            beans.setItemContens(list);
        }

        if("o_goods".equals(detailsBeans.getO_type_code())){
            beans.setType(V3SuccessBeans.TYPE.BUYGOODS);
            beans.setTitleText("订单");
            beans.setShowText("购买成功");
            beans.setBtn2Text("完成");
            List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

            V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent(detailsBeans.getO_name());
            itemConten.setItemTitle("商品名:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+ BaseUtil.getNoZoon(detailsBeans.getO_price()));
            itemConten.setItemTitle("单价:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("x"+detailsBeans.getO_num());
            itemConten.setItemTitle("数量:");
            list.add(itemConten);

            itemConten = new V3SuccessBeans.ItemConten();
            itemConten.setContent("¥"+BaseUtil.getNoZoon(detailsBeans.getO_money()));
            itemConten.setItemTitle("总价:");
            list.add(itemConten);

            if(BaseUtil.isValue(detailsBeans.getO_coupon_money()) && Double.valueOf(detailsBeans.getO_coupon_money()) > 0){
                itemConten = new V3SuccessBeans.ItemConten();
                itemConten.setContent("-¥"+BaseUtil.getNoZoon(detailsBeans.getO_coupon_money()));
                itemConten.setItemTitle("优惠:");
                list.add(itemConten);
            }

            beans.setItemContens(list);
        }



        return beans;
    }

    /*
    * 取消订单
    * */
    private void getCancelOrder(){
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(ConstantsCode.CANCLE_ORDER,Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof MineOrderDetailsBeans){
            detailsBeans = ((MineOrderDetailsBeans)data).getData().getInfo();
            setData();
        }

        if(data instanceof PointBean){
            pointBean = (PointBean)data;
            binding.minePoint.setText(BaseUtil.getNoZoon(pointBean.getData().getPoint()));
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CANCLE_ORDER){
            mActivity.showSuccess("成功取消订单！");
        }
    }

    /*
    * 获取规格字符串
    * */
    private String getNormStr(List<String> mList){
        if(mList == null || mList.size() <= 0){
            return "";
        }
        String normstr = "";
        for(String nor:mList){
            normstr += nor;
        }
        return normstr;
    }
}
