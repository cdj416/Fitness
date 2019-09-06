package com.hongyuan.fitness.ui.about_class.group_class.group_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyGroupClassBinding;

public class MyGroupClassActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_group_class;
    }

    @Override
    protected void initView() {
        setTitle("我的团体课");
        setsetImmersive(0x55000000);
        ActivityMyGroupClassBinding binding = ActivityMyGroupClassBinding.bind(mView);
        MyGroupClassViewModel viewModel = new MyGroupClassViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
