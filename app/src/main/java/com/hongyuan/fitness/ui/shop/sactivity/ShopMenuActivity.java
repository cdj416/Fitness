package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopMenuBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopMenuViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ShopMenuActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_menu;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"商品分类");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"商品分类");

        ActivityShopMenuBinding binding = ActivityShopMenuBinding.bind(mView);
        ShopMenuViewModel viewModel = new ShopMenuViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
