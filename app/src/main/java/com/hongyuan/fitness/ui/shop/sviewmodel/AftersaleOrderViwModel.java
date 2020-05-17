package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.scllor_view.UnitBeanUtils;
import com.hongyuan.fitness.databinding.ActivityAftersaleOrderBinding;
import com.hongyuan.fitness.ui.mall.good_pay.GoodsPayActivity;
import com.hongyuan.fitness.ui.mall.good_pay.PayDataBean;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.shop.sactivity.AfterSalesActivity;
import com.hongyuan.fitness.ui.shop.sactivity.LogisticsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ProductReviewActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SgoodsDetailActivity;
import com.hongyuan.fitness.ui.shop.sadapter.AfterSaleAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.CancelOrderReasonBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

import java.util.List;

public class AftersaleOrderViwModel extends CustomViewModel {
    // 订单状态  1是待支付 2已取消 3待发货，4已发货 5已提货 6待评价 8已完成 9是不能申请退款的终极完成
    public static final int STATU_PAY = 1;
    public static final int STATU_CANCEL = 2;
    public static final int STATU_DELIVERY = 3;
    public static final int STATU_SHIPPED = 4;
    public static final int STATU_PICKEDUP = 5;
    public static final int STATU_BE_EVALUATED = 6;
    public static final int STATU_COMPLETED = 8;
    public static final int STATU_ALL_COMPLETED = 9;



    private ActivityAftersaleOrderBinding binding;

    private AfterSaleAdapter adapter;

    //数据源
    private AftersaleOrderBeans.DataBean.InfoBean infoBean;

    private UnitBeanUtils utils;
    //原因集
    private List<CancelOrderReasonBeans.DataBean.ListBean> rList;

    public AftersaleOrderViwModel(CustomActivity mActivity, ActivityAftersaleOrderBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();

        lazyLoad();
    }

    @Override
    protected void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new AfterSaleAdapter();
        binding.mRec.setAdapter(adapter);




        binding.goLogistics.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("deliver_company_code",infoBean.getDeliver_info().getDeliver_company_code());
            bundle.putString("deliver_num",infoBean.getDeliver_info().getDeliver_num());
            startActivity(LogisticsActivity.class,bundle);
        });
        binding.goAfterSales.setOnClickListener(v ->
                startActivity(AfterSalesActivity.class,null));


        //取消订单
        binding.cancelOrdel.setOnClickListener(v -> {
            CustomDialog.scroller(mActivity, utils.getUnitList(rList), "订单取消", (view, message) -> {
                cancelOrder(getBundle().getString("o_id"),utils.getUseId(message));
            });
        });
        //去从新支付
        binding.goPay.setOnClickListener(v -> {
            PayDataBean payDataBean = new PayDataBean();
            payDataBean.setO_id(getBundle().getString("o_id"));
            payDataBean.setShowPoint("");
            payDataBean.setShowPrice(infoBean.getNeed_money());
            payDataBean.setLavePoint("");
            Bundle bundle = new Bundle();
            bundle.putSerializable("payDataBean",payDataBean);
            bundle.putSerializable("successBeans",getSuccessBeans());
            startActivity(GoodsPayActivity.class,bundle);
        });

        //提醒发货
        binding.dDelivery.setOnClickListener(v -> {
            CustomDialog.showMessage(mActivity,"已催促商家发货！");
        });

        //延长收货
        binding.lateDelivery.setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "确定延长收货？每笔订单只能延长一次哦？", "确定延长", "再想想", false, v1 -> {

            });
        });

        //查看物流
        binding.goLookLogistics.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("deliver_company_code",infoBean.getDeliver_info().getDeliver_company_code());
            bundle.putString("deliver_num",infoBean.getDeliver_info().getDeliver_num());
            startActivity(LogisticsActivity.class,bundle);
        });

        //确定收货
        binding.confirmReceipt.setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "是否确认收货？", "确定收货", "在想想", false, view -> {
                if(view.getId() == R.id.isOk){
                    confirmOrder();
                }
            });
        });

        //再次购买
        binding.goAfterSales.setOnClickListener(v -> {
            if(infoBean.getGoods_list().get(0).getG_id() != 0){
                Bundle bundle = new Bundle();
                bundle.putString("g_id",String.valueOf(infoBean.getGoods_list().get(0).getG_id()));
                startActivity(SgoodsDetailActivity.class,bundle);
            }else{
                CustomDialog.showMessage(mActivity,"商品已下架");
            }
        });

        //评价
        binding.goEvaluation.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("o_id",getBundle().getString("o_id"));
            startActivity(ProductReviewActivity.class,bundle);

            mActivity.finish();
        });
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

    //取消订单原因
    private void getReason(){
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_CANCEL_REASON_LIST,Controller.TYPE_POST,getParams(), CancelOrderReasonBeans.class,this);
    }

    //取消订单
    private void cancelOrder(String o_id,String reason_id){
        mActivity.showLoading();
        clearParams().setParams("o_id",o_id).setParams("reason_id",reason_id);
        Controller.myRequest(ConstantsCode.CANCLE_ORDER,Constants.CANCLE_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    //确认订单
    private void confirmOrder(){
        mActivity.showLoading();
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(ConstantsCode.CONFIRM_ORDER,Constants.CONFIRM_ORDER,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }


    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("o_id",getBundle().getString("o_id"));
        Controller.myRequest(Constants.GET_ORDER_INFO,Controller.TYPE_POST,getParams(), AftersaleOrderBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof AftersaleOrderBeans){
            infoBean = ((AftersaleOrderBeans)data).getData().getInfo();

            if(infoBean.getState() == STATU_PAY){
                binding.statusName.setText("等待买家付款");
                binding.logisticsBox.setVisibility(View.GONE);
                binding.payTime.setVisibility(View.GONE);
                binding.payType.setVisibility(View.GONE);

                binding.logisticsBox.setVisibility(View.GONE);

                binding.waitPayBox.setVisibility(View.VISIBLE);

                //去请求取消订单原因
                getReason();
            }
            if(infoBean.getState() == STATU_DELIVERY){
                binding.statusName.setText("等待卖家发货");
                binding.logisticsBox.setVisibility(View.GONE);

                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.logisticsBox.setVisibility(View.GONE);

                binding.dDeliveryBox.setVisibility(View.VISIBLE);
            }
            if(infoBean.getState() == STATU_SHIPPED){
                binding.statusName.setText("卖家已发货");
                binding.logisticsBox.setVisibility(View.VISIBLE);
                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.deliverCompany.setText(infoBean.getDeliver_info().getDeliver_company());
                binding.content.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getContext() : infoBean.getDeliver_info().getDeliver_num());
                binding.delTime.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getTime() : infoBean.getDeliver_info().getDeliver_date());

                binding.shippedBox.setVisibility(View.VISIBLE);
            }
            if(infoBean.getState() == STATU_BE_EVALUATED){
                binding.statusName.setText("待评价");
                binding.logisticsBox.setVisibility(View.VISIBLE);
                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.deliverCompany.setText(infoBean.getDeliver_info().getDeliver_company());
                binding.content.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getContext() : infoBean.getDeliver_info().getDeliver_num());
                binding.delTime.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getTime() : infoBean.getDeliver_info().getDeliver_date());

                binding.beEvaluatedBox.setVisibility(View.VISIBLE);
            }

            binding.telNum.setText(infoBean.getDeliver_address().getDeliver_mobile());
            binding.address.setText(infoBean.getDeliver_address().getPname()+" "+infoBean.getDeliver_address().getCname()+" "+infoBean.getDeliver_address().getDname()+" "+infoBean.getDeliver_address().getAddress());

            binding.storeName.setText(infoBean.getStore().getStore_name());

            binding.allPrice.setRightText(BaseUtil.getNoZoon(infoBean.getAll_product_price()));
            binding.freight.setRightText(BaseUtil.getNoZoon(infoBean.getDeliver_fee()));
            binding.coupon.setRightText(BaseUtil.getNoZoon(infoBean.getCoupon_money()));
            binding.needPayPrice.setRightText(BaseUtil.getNoZoon(infoBean.getNeed_money()));
            //binding.payType.setRightText(infoBean.getpay);

            binding.notes.setRightText(infoBean.getNote());
            binding.orderNum.setRightText(infoBean.getOrder_sn());
            binding.createTime.setRightText(infoBean.getAdd_date());


            adapter.setNewData(infoBean.getGoods_list());
        }

        if(data instanceof CancelOrderReasonBeans){
            rList = ((CancelOrderReasonBeans)data).getData().getList();

            utils = new UnitBeanUtils<CancelOrderReasonBeans.DataBean.ListBean>() {
                @Override
                public String unit(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return String.valueOf(o.getReason_id());
                }

                @Override
                public String unit_cn(CancelOrderReasonBeans.DataBean.ListBean o) {
                    return o.getName();
                }
            };
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.CANCLE_ORDER){
            mActivity.showSuccess("订单已取消");
        }

        if(code == ConstantsCode.CONFIRM_ORDER){
            mActivity.showSuccess("已确认收货");
        }
    }
}
