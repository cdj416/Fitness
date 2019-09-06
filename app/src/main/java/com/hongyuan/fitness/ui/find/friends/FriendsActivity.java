package com.hongyuan.fitness.ui.find.friends;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFriendsBinding;

public class FriendsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initView() {
        setTitle("我关注的人");
        setsetImmersive(0x55000000);
        ActivityFriendsBinding binding = ActivityFriendsBinding.bind(mView);
        FriendsViewModel viewModel = new FriendsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
