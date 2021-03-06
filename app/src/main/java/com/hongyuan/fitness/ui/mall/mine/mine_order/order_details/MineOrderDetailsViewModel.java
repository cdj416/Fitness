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
import com.hongyuan.fitness.ui.mall.good_order_details.PointBean;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.util.BaseUtil;

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
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.a_testbaner3).error(R.mipmap.a_testbaner3);
        Glide.with(mActivity).load(detailsBeans.getO_img()).apply(options).into(binding.goodImg);

        binding.goodName.setText(detailsBeans.getO_name());
        binding.norm.setText(getNormStr(detailsBeans.getSku()));
        binding.goodNum.setText("x"+detailsBeans.getO_num());
        binding.unitPrice.setText(BaseUtil.getNoZoon(detailsBeans.getO_price()));
        binding.buyNum.setText(String.valueOf(detailsBeans.getO_num()));
        binding.buyPoint.setText(BaseUtil.getNoZoon(detailsBeans.getO_point()));
        binding.orderAddTime.setText(detailsBeans.getAdd_date());
        binding.orderNum.setText(detailsBeans.getO_sn());
        binding.orderPayTime.setText(detailsBeans.getPay_date());

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
            showSuccess("成功取消订单！");
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
