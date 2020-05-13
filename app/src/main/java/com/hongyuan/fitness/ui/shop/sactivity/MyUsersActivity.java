package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyUsersBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MyUsersViewModel;

public class MyUsersActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_users;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"我的用户");

        ActivityMyUsersBinding binding = ActivityMyUsersBinding.bind(mView);
        MyUsersViewModel viewModel = new MyUsersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
