package com.hongyuan.fitness.ui.about_class.registration_group;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRegistrationGroupBinding;

public class RegistrationGroupActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registration_group;
    }

    @Override
    protected void initView() {
        setTitle("团课报名");
        setsetImmersive(0x55000000);
        ActivityRegistrationGroupBinding binding = ActivityRegistrationGroupBinding.bind(mView);
        RegistrationGroupViewModel viewModel = new RegistrationGroupViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
