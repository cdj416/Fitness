package com.hongyuan.fitness.ui.only_equipment.smart_basic_information;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySmartBasicInformationBinding;

public class SmartBasicInformationActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_smart_basic_information;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"基本资料");
        ActivitySmartBasicInformationBinding binding = ActivitySmartBasicInformationBinding.bind(mView);
        SmartBasicInformationViewModel viewModel = new SmartBasicInformationViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
