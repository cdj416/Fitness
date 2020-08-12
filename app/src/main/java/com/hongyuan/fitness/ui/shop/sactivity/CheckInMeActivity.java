package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCheckInMeBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CheckInMeViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class CheckInMeActivity extends CustomActivity {

    private CheckInMeViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_check_in_me;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"入驻随动");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"入驻随动");

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
