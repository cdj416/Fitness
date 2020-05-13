package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRewardWithdrawalBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RewardWithdrawalViewModel;

public class RewardWithdrawalActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reward_withdrawal;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"奖励提取");

        ActivityRewardWithdrawalBinding binding = ActivityRewardWithdrawalBinding.bind(mView);
        RewardWithdrawalViewModel viewModel = new RewardWithdrawalViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
