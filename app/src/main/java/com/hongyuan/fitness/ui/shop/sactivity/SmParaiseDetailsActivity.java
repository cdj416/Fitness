package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySmParaiseDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SmParaiseDetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class SmParaiseDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sm_paraise_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"赞我的人");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"赞我的人");

        ActivitySmParaiseDetailsBinding binding = ActivitySmParaiseDetailsBinding.bind(mView);
        SmParaiseDetailsViewModel viewModel = new SmParaiseDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
