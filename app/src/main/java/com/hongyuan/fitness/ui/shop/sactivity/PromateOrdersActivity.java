package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromateOrdersBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.PromateOrdersViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class PromateOrdersActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promate_orders;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"推广订单");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"推广订单");

        ActivityPromateOrdersBinding binding = ActivityPromateOrdersBinding.bind(mView);
        PromateOrdersViewModel viewModel = new PromateOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
