package com.hongyuan.fitness.ui.membership_card.my_card_detail;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyCardDetailsBinding;

public class MyCardDetailsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_card_details;
    }

    @Override
    protected void initView() {
        setTypeBar("会籍卡",R.drawable.shape_gradient_h_39_4a);
        ActivityMyCardDetailsBinding binding = ActivityMyCardDetailsBinding.bind(mView);
        MyCardDetailsViewModel viewModel = new MyCardDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.dd_mask_in,R.anim.dd_mask_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.dd_mask_out);
    }
}
