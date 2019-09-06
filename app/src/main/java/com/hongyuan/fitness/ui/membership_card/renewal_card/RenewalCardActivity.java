package com.hongyuan.fitness.ui.membership_card.renewal_card;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRenewalCardBinding;

public class RenewalCardActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_renewal_card;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"续卡");
        ActivityRenewalCardBinding binding = ActivityRenewalCardBinding.bind(mView);
        RenewalCardViewModel viewModel = new RenewalCardViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
