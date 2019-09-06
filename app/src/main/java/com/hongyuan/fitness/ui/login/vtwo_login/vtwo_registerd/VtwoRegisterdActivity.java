package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_registerd;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityVtwoRegisteredBinding;

public class VtwoRegisterdActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_registered;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoRegisteredBinding binding = ActivityVtwoRegisteredBinding.bind(mView);
        VtwoRegisterdViewModel viewModel = new VtwoRegisterdViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
