package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityCustomServerBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CustomServerViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class CustomServerActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_custom_server;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"随动客服");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"随动客服");

        AcitivityCustomServerBinding binding = AcitivityCustomServerBinding.bind(mView);
        CustomServerViewModel viewModel = new CustomServerViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
