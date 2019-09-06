package com.hongyuan.fitness.ui.person.about_us;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        setTitle("关于我们");
        setsetImmersive(0x55000000);
        ActivityAboutUsBinding binding = ActivityAboutUsBinding.bind(mView);
        AboutUsViewModel viewModel = new AboutUsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
