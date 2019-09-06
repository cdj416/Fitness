package com.hongyuan.fitness.ui.person.my_fan;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFansBinding;

public class MyFansActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initView() {
        setTitle("我的粉丝");
        setsetImmersive(0x55000000);
        ActivityFansBinding binding = ActivityFansBinding.bind(mView);
        MyFansViewModel viewModel = new MyFansViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
