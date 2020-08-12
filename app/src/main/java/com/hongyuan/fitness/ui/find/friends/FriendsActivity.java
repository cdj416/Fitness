package com.hongyuan.fitness.ui.find.friends;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFriendsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class FriendsActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我关注的人");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我关注的人");

        ActivityFriendsBinding binding = ActivityFriendsBinding.bind(mView);
        FriendsViewModel viewModel = new FriendsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
