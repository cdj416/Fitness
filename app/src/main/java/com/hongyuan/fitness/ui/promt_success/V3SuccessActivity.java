package com.hongyuan.fitness.ui.promt_success;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SuccessBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class V3SuccessActivity extends CustomActivity {

    private V3SuccessViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_success;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR3,R.drawable.theme_shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"");

        ActivityV3SuccessBinding binding = ActivityV3SuccessBinding.bind(mView);
        viewModel = new V3SuccessViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
