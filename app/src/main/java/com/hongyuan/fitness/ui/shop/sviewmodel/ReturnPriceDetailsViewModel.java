package com.hongyuan.fitness.ui.shop.sviewmodel;

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
import com.hongyuan.fitness.databinding.ActivityReturnPriceDetailsBinding;
import com.hongyuan.fitness.ui.shop.sactivity.EdLogisticsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.RefundGoodsActivity;
import com.hongyuan.fitness.ui.shop.sactivity.RefundPricesActivity;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.ui.shop.sbeans.ReturnDetailsBeans;
import com.hongyuan.fitness.ui.shop.sinterface.MyClickListener;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.BottomReturnPriceDetailsViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

public class ReturnPriceDetailsViewModel extends CustomViewModel implements MyClickListener {

    ////退款状态 1已申请 2已取消 3已驳回 4重新申请 5商家同意退款 6买家发货退回 7卖家拒绝确认收货 8卖家确认收到货  9已完成

    private ActivityReturnPriceDetailsBinding binding;

    private ReturnDetailsBeans.DataBean.InfoBean detailsBeans;
    private BottomReturnPriceDetailsViewModel bottomViewModel;


    public ReturnPriceDetailsViewModel(CustomActivity mActivity, ActivityReturnPriceDetailsBinding binding, BottomReturnPriceDetailsViewModel bottomViewModel) {
        super(mActivity);
        this.binding = binding;
        this.bottomViewModel = bottomViewModel;

        initView();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("opg_id",getBundle().getString("opg_id"));
        Controller.myRequest(Constants.GET_REFUND_INFO,Controller.TYPE_POST,getParams(), ReturnDetailsBeans.class,this);
    }

    @Override
    protected void setData() {
        //去获取客服信息
        bottomViewModel.getKeFu(String.valueOf(detailsBeans.getStore_id()));

        //设置头部变动信息
        binding.topView.setStatus(detailsBeans,this);

        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mActivity).load(detailsBeans.getG_img()).apply(options).into(binding.goodsImg);
        binding.goodName.setText(detailsBeans.getG_name());
        binding.goodPrice.setText(BaseUtil.getNoZoon(detailsBeans.getGp_price()));
        binding.goodNum.setText("x"+detailsBeans.getBuy_num());

        if(BaseUtil.isValue(detailsBeans.getSku_names())){
            binding.goodSku.setText(detailsBeans.getSku_names());
        }else{
            binding.goodSku.setVisibility(View.INVISIBLE);
        }

        binding.reasonName.setText("退款原因："+detailsBeans.getReason_name());
        binding.returnMoney.setText("退款金额：¥"+BaseUtil.getNoZoon(detailsBeans.getRefund_money()));
        binding.returnNum.setText("退款编号："+detailsBeans.getOut_request_no());


    }

    /*
     * 撤销退款
     * */
    private void cancelReturn(){
        clearParams().setParams("opg_id",String.valueOf(detailsBeans.getOpg_id()));
        Controller.myRequest(ConstantsCode.CANCEL_APPLY_REFUND,Constants.CANCEL_APPLY_REFUND,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof ReturnDetailsBeans){
            detailsBeans = ((ReturnDetailsBeans)data).getData().getInfo();

            setData();
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.CANCEL_APPLY_REFUND){
            lazyLoad();
            mActivity.showSuccess("撤销成功！");
        }
    }

    @Override
    public void onMyClick(View v) {
        AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean bean = new AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean();
        bean.setBuy_num(detailsBeans.getBuy_num());
        bean.setG_id(detailsBeans.getG_id());
        bean.setG_img(detailsBeans.getG_img());
        bean.setG_name(detailsBeans.getG_name());
        bean.setGp_point(detailsBeans.getPoint());
        bean.setGp_price(detailsBeans.getGp_price());
        bean.setOpg_id(detailsBeans.getOpg_id());
        bean.setRefund_state(detailsBeans.getRefund_state());
        bean.setSku_names(detailsBeans.getSku_names());


        switch (v.getId()){
            case R.id.cancelApply4:
            case R.id.cancelApply3:
            case R.id.cancelApply2:
            case R.id.cancelApply1:
                CustomDialog.promptDialog(mActivity, "确定要撤销退款？", "确定", "取消", false, v1 -> {
                    if(v1.getId() == R.id.isOk){
                        cancelReturn();
                    }
                });
                break;

            case R.id.editApply3:
                Bundle bundle0 = new Bundle();
                bundle0.putSerializable("item",bean);
                bundle0.putInt("is_refund",-1);
                bundle0.putInt("is_return_goods",detailsBeans.getIs_return_goods());
                startActivity(RefundGoodsActivity.class,bundle0);
                break;
            case R.id.editApply1:
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",bean);
                bundle.putInt("is_refund",1);
                bundle.putInt("state",0);
                startActivity(RefundPricesActivity.class,bundle);
                break;

            case R.id.editLogics:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("item",bean);
                bundle1.putString("opg_id",String.valueOf(detailsBeans.getOpg_id()));
                startActivity(EdLogisticsActivity.class,bundle1);
                break;
        }
    }
}
