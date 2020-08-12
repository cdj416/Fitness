package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopStoreBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SstoreViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class SstoreActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_store;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR1,R.drawable.theme_shape_soid_ffffff,"店铺主页");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"店铺主页");

        ActivityShopStoreBinding binding = ActivityShopStoreBinding.bind(mView);
        SstoreViewModel viewModel = new SstoreViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
