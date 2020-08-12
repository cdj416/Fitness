package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityRefundPricesBinding;
import com.hongyuan.fitness.ui.shop.sactivity.RefundGoodsActivity;
import com.hongyuan.fitness.ui.shop.sbeans.AftersaleOrderBeans;
import com.hongyuan.fitness.util.BaseUtil;

public class RefundPricesViewModel extends CustomViewModel {

    private AcitivityRefundPricesBinding binding;

    private AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean item;

    public RefundPricesViewModel(CustomActivity mActivity, AcitivityRefundPricesBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {

        setEnableOverScrollDrag(true);
        int state = getBundle().getInt("state");
        if(state == AftersaleOrderViwModel.STATU_DELIVERY){
            binding.refundGoods.setVisibility(View.GONE);
        }else{
            binding.refundGoods.setVisibility(View.VISIBLE);
        }

        item = (AftersaleOrderBeans.DataBean.InfoBean.GoodsListBean) getBundle().getSerializable("item");
        RequestOptions options = new RequestOptions().placeholder(R.color.color_f2).error(R.color.color_f2);
        Glide.with(mActivity).load(item.getG_img()).apply(options).into(binding.goodsImg);
        binding.goodName.setText(item.getG_name());
        binding.goodPrice.setText(BaseUtil.getNoZoon(item.getGp_price()));
        binding.goodNum.setText("x"+item.getBuy_num());

        if(BaseUtil.isValue(item.getSku_names())){
            binding.goodSku.setText(item.getSku_names());
        }else{
            binding.goodSku.setVisibility(View.INVISIBLE);
        }


        binding.returnPrice.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item",item);
            bundle.putInt("is_return_goods",2);
            bundle.putInt("is_refund",getBundle().getInt("is_refund"));
            startActivity(RefundGoodsActivity.class,bundle);
        });

        binding.refundGoods.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item",item);
            bundle.putInt("is_return_goods",1);
            bundle.putInt("is_refund",getBundle().getInt("is_refund"));
            startActivity(RefundGoodsActivity.class,bundle);
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
