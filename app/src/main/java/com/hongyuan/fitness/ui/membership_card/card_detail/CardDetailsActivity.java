package com.hongyuan.fitness.ui.membership_card.card_detail;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCardDetailsBinding;

public class CardDetailsActivity extends CustomActivity {

    private CardDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_details;
    }

    @Override
    protected void initView() {
        setTitle("会籍卡详情");
        setsetImmersive(0x55000000);
        ActivityCardDetailsBinding binding = ActivityCardDetailsBinding.bind(mView);
        viewModel = new CardDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
