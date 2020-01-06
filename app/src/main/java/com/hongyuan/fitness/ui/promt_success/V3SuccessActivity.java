package com.hongyuan.fitness.ui.promt_success;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV3SuccessBinding;

public class V3SuccessActivity extends CustomActivity {

    private V3SuccessViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v3_success;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,0,"");
        ActivityV3SuccessBinding binding = ActivityV3SuccessBinding.bind(mView);
        viewModel = new V3SuccessViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
