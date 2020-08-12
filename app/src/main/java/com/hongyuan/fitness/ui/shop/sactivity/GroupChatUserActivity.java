package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGroupChatUserBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.GroupChatUserViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class GroupChatUserActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_chat_user;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"成员列表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"成员列表");

        ActivityGroupChatUserBinding binding = ActivityGroupChatUserBinding.bind(mView);
        GroupChatUserViewModel viewModel = new GroupChatUserViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
