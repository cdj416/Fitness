package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityShopAddressBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopAddressViewModel;

public class ShopAddressActivity extends CustomActivity {


    private ShopAddressViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_shop_address;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"收货地址");

        AcitivityShopAddressBinding binding = AcitivityShopAddressBinding.bind(mView);
        viewModel = new ShopAddressViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
