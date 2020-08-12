package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityIncomeFormBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.IncomeFormViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class IncomeFormActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_income_form;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"收益报表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"收益报表");

        AcitivityIncomeFormBinding binding = AcitivityIncomeFormBinding.bind(mView);
        IncomeFormViewModel viewModel = new IncomeFormViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
