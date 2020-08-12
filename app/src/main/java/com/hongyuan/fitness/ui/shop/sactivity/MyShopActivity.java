package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityMyShopBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MyShopViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class MyShopActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_my_shop;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
        setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的商城");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的商城");

        AcitivityMyShopBinding binding = AcitivityMyShopBinding.bind(mView);
        MyShopViewModel viewModel = new MyShopViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
