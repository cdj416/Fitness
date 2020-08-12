package com.hongyuan.fitness.ui.membership_card.card_detail;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCardDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CardDetailsActivity extends CustomActivity {

    private CardDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"会籍卡详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"会籍卡详情");

        ActivityCardDetailsBinding binding = ActivityCardDetailsBinding.bind(mView);
        viewModel = new CardDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
