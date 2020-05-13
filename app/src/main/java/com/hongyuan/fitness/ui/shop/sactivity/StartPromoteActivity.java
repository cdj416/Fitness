package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityStartPromoteBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.StartPromoteViewModel;

public class StartPromoteActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_start_promote;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"开始推广");

        AcitivityStartPromoteBinding binding = AcitivityStartPromoteBinding.bind(mView);
        StartPromoteViewModel viewModel = new StartPromoteViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
