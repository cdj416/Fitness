package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityIncomeMangeBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IncomeMangeViewModel;

public class IncomeMangeActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_income_mange;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"收益管理");

        AcitivityIncomeMangeBinding binding = AcitivityIncomeMangeBinding.bind(mView);
        IncomeMangeViewModel viewModel = new IncomeMangeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
