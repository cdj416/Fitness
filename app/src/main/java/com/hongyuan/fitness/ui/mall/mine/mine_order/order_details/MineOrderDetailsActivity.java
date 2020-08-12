package com.hongyuan.fitness.ui.mall.mine.mine_order.order_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineOrderDetailBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MineOrderDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_order_detail;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"订单详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"订单详情");

        ActivityMineOrderDetailBinding binding = ActivityMineOrderDetailBinding.bind(mView);
        MineOrderDetailsViewModel viewModel = new MineOrderDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
