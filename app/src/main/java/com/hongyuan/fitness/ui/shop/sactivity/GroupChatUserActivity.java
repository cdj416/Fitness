package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGroupChatUserBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.GroupChatUserViewModel;

public class GroupChatUserActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_chat_user;
    }

    @Override
    protected void initView() {

        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"成员列表");

        ActivityGroupChatUserBinding binding = ActivityGroupChatUserBinding.bind(mView);
        GroupChatUserViewModel viewModel = new GroupChatUserViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
