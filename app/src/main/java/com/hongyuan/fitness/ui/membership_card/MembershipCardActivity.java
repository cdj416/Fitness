package com.hongyuan.fitness.ui.membership_card;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMembershipCardBinding;

public class MembershipCardActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_membership_card;
    }

    @Override
    protected void initView() {
        setTypeBar("会籍卡",R.drawable.shape_gradient_h_39_4a);
        ActivityMembershipCardBinding binding = ActivityMembershipCardBinding.bind(mView);
        MembershipCardViewModel viewModel = new MembershipCardViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
