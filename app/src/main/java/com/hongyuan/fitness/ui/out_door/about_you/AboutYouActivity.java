package com.hongyuan.fitness.ui.out_door.about_you;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAboutYouBinding;

public class AboutYouActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_you;
    }

    @Override
    protected void initView() {
        setTitle("关于你");
        setsetImmersive(0x55000000);
        ActivityAboutYouBinding binding = ActivityAboutYouBinding.bind(mView);
        AboutYouViewModel viewModel = new AboutYouViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
