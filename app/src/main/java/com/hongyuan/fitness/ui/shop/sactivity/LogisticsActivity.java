package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityLogisticsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.LogisticsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class LogisticsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"物流详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"物流详情");

        ActivityLogisticsBinding binding = ActivityLogisticsBinding.bind(mView);
        LogisticsViewModel viewModel = new LogisticsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
