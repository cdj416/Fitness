package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityCheckInMeBinding;
import com.hongyuan.fitness.ui.shop.sactivity.CheckInShopActivity;
import com.hongyuan.fitness.ui.shop.sbeans.CheckInMeBeans;

public class CheckInMeViewModel extends CustomViewModel {

    private AcitivityCheckInMeBinding binding;

    //检查结果
    private CheckInMeBeans.DataBean dataBean;

    public CheckInMeViewModel(CustomActivity mActivity, AcitivityCheckInMeBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        setEnableOverScrollDrag(true);

        binding.checkInShop.setOnClickListener(v -> {
            if(dataBean.getInfo1().getIs_tj() == 0 || (dataBean.getInfo1().getIs_tj() == 1 && dataBean.getInfo1().getIs_pass() == 0)){
                Bundle bundle = new Bundle();
                bundle.putInt("type",CheckInShopViewModel.SHOW_SHOP);
                bundle.putInt("state",dataBean.getInfo1().getIs_tj());
                startActivity(CheckInShopActivity.class,bundle);
            }else{

            }

        });
        binding.checkInVenue.setOnClickListener(v -> {
            if(dataBean.getInfo2().getIs_tj() == 0 ||(dataBean.getInfo2().getIs_tj() == 1 && dataBean.getInfo2().getIs_pass() == 0)){
                Bundle bundle = new Bundle();
                bundle.putInt("type",CheckInShopViewModel.SHOW_GYM);
                bundle.putInt("state",dataBean.getInfo2().getIs_tj());
                startActivity(CheckInShopActivity.class,bundle);
            }

        });
        binding.checkInGym.setOnClickListener(v -> {
            if(dataBean.getInfo3().getIs_tj() == 0 ||(dataBean.getInfo3().getIs_tj() == 1 && dataBean.getInfo3().getIs_pass() == 0)){
                Bundle bundle = new Bundle();
                bundle.putInt("type",CheckInShopViewModel.SHOW_VENUE);
                bundle.putInt("state",dataBean.getInfo3().getIs_tj());
                startActivity(CheckInShopActivity.class,bundle);
            }

        });
    }

    @Override
    public void refreshData() {
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();

        //检查是否有申请
        clearParams();
        Controller.myRequest(Constants.GET_APPLY_STATE,Controller.TYPE_POST,getParams(), CheckInMeBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof CheckInMeBeans){
            dataBean = ((CheckInMeBeans)data).getData();
            if(dataBean.getInfo1().getIs_tj() == 0){
                binding.checkInShop.setText("申请入驻");
            }else if(dataBean.getInfo1().getIs_tj() == 1 && dataBean.getInfo1().getIs_pass() == 0){
                binding.checkInShop.setText("审核中...");
            }else if(dataBean.getInfo1().getIs_tj() == 1 && dataBean.getInfo1().getIs_pass() == 1){
                binding.checkInShop.setText("已成功入驻");
            }

            if(dataBean.getInfo2().getIs_tj() == 0){
                binding.checkInVenue.setText("申请入驻");
            }else if(dataBean.getInfo2().getIs_tj() == 1 && dataBean.getInfo2().getIs_pass() == 0){
                binding.checkInVenue.setText("审核中...");
            }else if(dataBean.getInfo2().getIs_tj() == 1 && dataBean.getInfo2().getIs_pass() == 1){
                binding.checkInVenue.setText("已成功入驻");
            }

            if(dataBean.getInfo3().getIs_tj() == 0){
                binding.checkInGym.setText("申请入驻");
            }else if(dataBean.getInfo3().getIs_tj() == 1 && dataBean.getInfo3().getIs_pass() == 0){
                binding.checkInGym.setText("审核中...");
            }else if(dataBean.getInfo3().getIs_tj() == 1 && dataBean.getInfo3().getIs_pass() == 1){
                binding.checkInGym.setText("已成功入驻");
            }
        }
    }
}
