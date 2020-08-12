package com.hongyuan.fitness.ui.person.about_us;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAboutUsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class AboutUsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"关于我们");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"关于我们");

        ActivityAboutUsBinding binding = ActivityAboutUsBinding.bind(mView);
        AboutUsViewModel viewModel = new AboutUsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
