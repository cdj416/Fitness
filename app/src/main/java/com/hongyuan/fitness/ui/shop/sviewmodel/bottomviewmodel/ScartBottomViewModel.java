package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.os.Bundle;
import android.view.View;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomScartBinding;
import com.hongyuan.fitness.ui.shop.sactivity.SorderDetailsActivity;
import com.hongyuan.fitness.ui.shop.sviewmodel.ScartViewModel;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;

public class ScartBottomViewModel extends CustomViewModel {

    private ActivityBottomScartBinding binding;

    //当前可操作模式true为购买商品模式，false为管理商品模式
    private boolean mode = true;

    //主内荣的viewmodel
    private ScartViewModel manViewModel;

    public ScartBottomViewModel(CustomActivity mActivity, ActivityBottomScartBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    public void setManViewModel(ScartViewModel manViewModel){
        this.manViewModel = manViewModel;
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {
            if(mode){
                String cartJson = manViewModel.getCartJson();
                if(BaseUtil.isValue(manViewModel.getCartJson())){
                    Bundle bundle = new Bundle();
                    bundle.putString("cartJson",cartJson);
                    startActivity(SorderDetailsActivity.class,bundle);
                }else{
                    CustomDialog.showMessage(mActivity,"请选择要购买的商品！");
                }

            }else{
                boolean flag = Boolean.parseBoolean(binding.selectAll.getTag().toString());
                //删除商品
                manViewModel.deletGoods(flag);
            }
        });

        binding.selectAll.setOnClickListener(v -> {
            if(!manViewModel.isData()){
                CustomDialog.showMessage(mActivity,"无商品，不可操作！");
                return;
            }

            boolean flag = Boolean.parseBoolean(binding.selectAll.getTag().toString());
            if(flag){
                binding.selectAllImg.setImageResource(R.mipmap.yuanquanquan_no_select_img);
                binding.selectAll.setTag("false");
            }else{
                binding.selectAllImg.setImageResource(R.mipmap.checkmark_circle);
                binding.selectAll.setTag("true");
            }
            manViewModel.changAllCheck(!flag);
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

    /*
    * 全选非全选模式
    * */
    public void changeAllSelect(boolean flag){
        if(flag){
            binding.selectAllImg.setImageResource(R.mipmap.checkmark_circle);
            binding.selectAll.setTag("true");
        }else{
            binding.selectAllImg.setImageResource(R.mipmap.yuanquanquan_no_select_img);
            binding.selectAll.setTag("false");
        }
    }

    /*
    * 设置选择之后显示的总价格
    * */
    public void setAllPric(String allPric){
        binding.allPrice.setText(allPric);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
