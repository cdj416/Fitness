package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopCollectBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopCollectViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ShopCollectActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_collect;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"商城收藏");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"商城收藏");

        ActivityShopCollectBinding binding = ActivityShopCollectBinding.bind(mView);
        ShopCollectViewModel viewModel = new ShopCollectViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
