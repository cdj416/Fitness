package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCustomServerBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CustomServerViewModel;

public class CustomServerActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_custom_server;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"随动客服");

        AcitivityCustomServerBinding binding = AcitivityCustomServerBinding.bind(mView);
        CustomServerViewModel viewModel = new CustomServerViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
