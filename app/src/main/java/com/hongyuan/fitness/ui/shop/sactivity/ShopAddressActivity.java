package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityShopAddressBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopAddressViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ShopAddressActivity extends CustomActivity {


    private ShopAddressViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_shop_address;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"收货地址");
            getMainTitle().setRightTextColor("添加",getResources().getColor(R.color.color_FF333333));
        }
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"收货地址");
            getMainTitle().setRightTextColor("添加",getResources().getColor(R.color.color_FFFFFF));
        }


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
