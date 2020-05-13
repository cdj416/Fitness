package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCheckInMeBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CheckInMeViewModel;

public class CheckInMeActivity extends CustomActivity {

    private CheckInMeViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_check_in_me;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"入驻随动");

        AcitivityCheckInMeBinding binding = AcitivityCheckInMeBinding.bind(mView);
        viewModel = new CheckInMeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
