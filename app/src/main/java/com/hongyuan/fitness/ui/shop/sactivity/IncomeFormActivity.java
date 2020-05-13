package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityIncomeFormBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IncomeFormViewModel;

public class IncomeFormActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_income_form;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"收益报表");

        AcitivityIncomeFormBinding binding = AcitivityIncomeFormBinding.bind(mView);
        IncomeFormViewModel viewModel = new IncomeFormViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
