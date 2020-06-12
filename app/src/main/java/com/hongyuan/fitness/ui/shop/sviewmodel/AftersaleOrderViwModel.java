package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAftersaleOrderBinding;
import com.hongyuan.fitness.ui.shop.sadapter.AfterSaleAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.AftersaleOrderBottomViewModel;
import com.hongyuan.fitness.util.BaseUtil;

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
    private AftersaleOrderBottomViewModel bottomViewModel;

    private AfterSaleAdapter adapter;

    //数据源
    private AftersaleOrderBeans.DataBean.InfoBean infoBean;

    public AftersaleOrderViwModel(CustomActivity mActivity, ActivityAftersaleOrderBinding binding,AftersaleOrderBottomViewModel bottomViewModel) {
        super(mActivity);
        this.binding = binding;
        this.bottomViewModel = bottomViewModel;

        initView();

        lazyLoad();
    }

    @Override
    protected void initView() {

        setEnableOverScrollDrag(true);

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        manager.setOrientation(RecyclerView.VERTICAL);
        binding.mRec.setLayoutManager(manager);
        adapter = new AfterSaleAdapter();
        binding.mRec.setAdapter(adapter);


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

            bottomViewModel.setInfoData(infoBean);

            if(infoBean.getState() == STATU_PAY){
                binding.statusName.setText("等待买家付款");
                binding.logisticsBox.setVisibility(View.GONE);
                binding.payTime.setVisibility(View.GONE);
                binding.payType.setVisibility(View.GONE);

                binding.logisticsBox.setVisibility(View.GONE);

            }
            if(infoBean.getState() == STATU_DELIVERY){
                binding.statusName.setText("等待卖家发货");
                binding.logisticsBox.setVisibility(View.GONE);

                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.logisticsBox.setVisibility(View.GONE);

            }
            if(infoBean.getState() == STATU_SHIPPED){
                binding.statusName.setText("卖家已发货");
                binding.logisticsBox.setVisibility(View.VISIBLE);
                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.deliverCompany.setText(infoBean.getDeliver_info().getDeliver_company());
                binding.content.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getContext() : infoBean.getDeliver_info().getDeliver_num());
                binding.delTime.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getTime() : infoBean.getDeliver_info().getDeliver_date());

            }
            if(infoBean.getState() == STATU_BE_EVALUATED){
                binding.statusName.setText("待评价");
                binding.logisticsBox.setVisibility(View.VISIBLE);
                binding.payTime.setRightText(infoBean.getPay_date());
                binding.payType.setRightText(infoBean.getPay_way());

                binding.deliverCompany.setText(infoBean.getDeliver_info().getDeliver_company());
                binding.content.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getContext() : infoBean.getDeliver_info().getDeliver_num());
                binding.delTime.setText(infoBean.getDeliver_info().getInfo() != null ? infoBean.getDeliver_info().getInfo().getTime() : infoBean.getDeliver_info().getDeliver_date());
            }

            if(infoBean.getDeliver_way() == 2){
                binding.selfMentionBox.setVisibility(View.VISIBLE);
                binding.goAddress.setVisibility(View.GONE);

                if(infoBean.getState() == STATU_PAY || infoBean.getState() == STATU_CANCEL){
                    binding.sCode.setText("*****");
                }else{
                    binding.sCode.setText(infoBean.getQuhuo_code());
                }
                binding.sName.setText(infoBean.getDeliver_address().getConsignee());
                binding.sTel.setText(infoBean.getDeliver_address().getDeliver_mobile());
                binding.sAddress.setText(infoBean.getDeliver_address().getPname()+" "+infoBean.getDeliver_address().getCname()+" "+infoBean.getDeliver_address().getDname()+" "+infoBean.getDeliver_address().getAddress());
            }else{
                binding.selfMentionBox.setVisibility(View.GONE);
                binding.goAddress.setVisibility(View.VISIBLE);

                binding.receiptName.setText(infoBean.getDeliver_address().getConsignee());
                binding.telNum.setText(infoBean.getDeliver_address().getDeliver_mobile());
                binding.address.setText(infoBean.getDeliver_address().getPname()+" "+infoBean.getDeliver_address().getCname()+" "+infoBean.getDeliver_address().getDname()+" "+infoBean.getDeliver_address().getAddress());
            }


            binding.storeName.setText(infoBean.getStore().getStore_name());

            binding.allPrice.setRightText("¥"+BaseUtil.getNoZoon(infoBean.getAll_product_price()));
            binding.freight.setRightText("¥"+BaseUtil.getNoZoon(infoBean.getDeliver_fee()));
            binding.coupon.setRightText("¥"+BaseUtil.getNoZoon(infoBean.getCoupon_money()));
            binding.needPayPrice.setRightText("¥"+BaseUtil.getNoZoon(infoBean.getNeed_money()));
            //binding.payType.setRightText(infoBean.getpay);

            binding.notes.setRightText(infoBean.getNote());
            binding.orderNum.setRightText(infoBean.getOrder_sn());
            binding.createTime.setRightText(infoBean.getAdd_date());


            adapter.setNewData(infoBean.getGoods_list());
        }
    }
}
