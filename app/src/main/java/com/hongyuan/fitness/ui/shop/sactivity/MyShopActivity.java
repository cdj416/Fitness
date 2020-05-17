package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityMyShopBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MyShopViewModel;

public class MyShopActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_my_shop;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"我的商城");

        AcitivityMyShopBinding binding = AcitivityMyShopBinding.bind(mView);
        MyShopViewModel viewModel = new MyShopViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
