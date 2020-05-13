package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityIncomeMangeBinding;
import com.hongyuan.fitness.ui.shop.sactivity.IncomeFormActivity;
import com.hongyuan.fitness.ui.shop.sactivity.MyUsersActivity;
import com.hongyuan.fitness.ui.shop.sactivity.PromateOrdersActivity;
import com.hongyuan.fitness.ui.shop.sactivity.RewardWithdrawalActivity;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.StartPromoteActivity;

public class IncomeMangeViewModel extends CustomViewModel {

    private AcitivityIncomeMangeBinding binding;

    public IncomeMangeViewModel(CustomActivity mActivity, AcitivityIncomeMangeBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightImage(R.mipmap.person_message_mark)
                .setOnClickListener(v -> startActivity(ShopMessageActivity.class,null));

        binding.goIncomeForm.setOnClickListener(v -> startActivity(IncomeFormActivity.class,null));
        binding.goMyUsers.setOnClickListener(v -> startActivity(MyUsersActivity.class,null));
        binding.goPromote.setOnClickListener(v -> startActivity(StartPromoteActivity.class,null));
        binding.goPromateBox.setOnClickListener(v -> startActivity(PromateOrdersActivity.class,null));
        binding.goRewardWithdrawal.setOnClickListener(v -> startActivity(RewardWithdrawalActivity.class,null));
    }

    @Override
    public void onSuccess(Object data) {

    }
}
