package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShopMessageBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ShopMessageViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ShopMessageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_message;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的消息");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的消息");

        ActivityShopMessageBinding binding = ActivityShopMessageBinding.bind(mView);
        ShopMessageViewModel viewModel = new ShopMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
