package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPoitionDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.PotionDetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class PoitionDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_poition_details;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"积分账单");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"积分账单");

        ActivityPoitionDetailsBinding binding = ActivityPoitionDetailsBinding.bind(mView);
        PotionDetailsViewModel viewModel = new PotionDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);

    }
}
