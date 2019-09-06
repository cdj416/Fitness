package com.hongyuan.fitness.ui.membership_card.card_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCardDetailsBinding;

public class CardDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_details;
    }

    @Override
    protected void initView() {
        setTitle("会籍卡详情");
        setsetImmersive(0x55000000);
        ActivityCardDetailsBinding binding = ActivityCardDetailsBinding.bind(mView);
        CardDetailsViewModel viewModel = new CardDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
