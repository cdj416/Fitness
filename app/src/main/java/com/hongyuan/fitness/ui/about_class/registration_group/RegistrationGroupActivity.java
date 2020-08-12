package com.hongyuan.fitness.ui.about_class.registration_group;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRegistrationGroupBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class RegistrationGroupActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_registration_group;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"团课报名");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"团课报名");

        ActivityRegistrationGroupBinding binding = ActivityRegistrationGroupBinding.bind(mView);
        RegistrationGroupViewModel viewModel = new RegistrationGroupViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
