package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.view.View;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomScartBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SorderPaySuccessActivity;

public class ScartBottomViewModel extends CustomViewModel {

    private ActivityBottomScartBinding binding;

    //当前可操作模式false为购买商品模式，true为管理商品模式
    private boolean mode = false;

    public ScartBottomViewModel(CustomActivity mActivity, ActivityBottomScartBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {
            if(!mode){
                startActivity(SorderPaySuccessActivity.class,null);
            }else{

            }
        });
    }

    /*
    * 购买、管理模式切换
    * */
    public void changeModel(boolean flag){
        this.mode = flag;
        if(!flag){
            binding.allPriceBox.setVisibility(View.GONE);
            binding.submit.setText("删除");
        }else{
            binding.allPriceBox.setVisibility(View.VISIBLE);
            binding.submit.setText("结算");
        }
    }

    @Override
    public void onSuccess(Object data) {

    }
}
