package com.hongyuan.fitness.ui.person.newedition.activity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityMemberCardOrdersBinding;
import com.hongyuan.fitness.ui.person.newedition.viewmodel.MemberCardOrdersViewModel;

public class MemberCardOrdersActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_member_card_orders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"会员卡订单");

        AcitivityMemberCardOrdersBinding binding = AcitivityMemberCardOrdersBinding.bind(mView);
        MemberCardOrdersViewModel viewModel = new MemberCardOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
