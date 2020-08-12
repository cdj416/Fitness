package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRewardWithdrawalBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RewardWithdrawalViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class RewardWithdrawalActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reward_withdrawal;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"选择城市");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"选择城市");

        ActivityRewardWithdrawalBinding binding = ActivityRewardWithdrawalBinding.bind(mView);
        RewardWithdrawalViewModel viewModel = new RewardWithdrawalViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
