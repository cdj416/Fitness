package com.hongyuan.fitness.ui.membership_card;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMembershipCardBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MembershipCardActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_membership_card;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.shape_gradient_h_39_4a,"会籍卡");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.shape_gradient_h_39_4a_black,"会籍卡");

        ActivityMembershipCardBinding binding = ActivityMembershipCardBinding.bind(mView);
        MembershipCardViewModel viewModel = new MembershipCardViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
