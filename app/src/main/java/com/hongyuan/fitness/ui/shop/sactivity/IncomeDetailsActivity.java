package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityIncomeDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IncomeDetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class IncomeDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_income_details;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"结算订单明细");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"结算订单明细");

        ActivityIncomeDetailsBinding binding = ActivityIncomeDetailsBinding.bind(mView);
        IncomeDetailsViewModel viewModel = new IncomeDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
