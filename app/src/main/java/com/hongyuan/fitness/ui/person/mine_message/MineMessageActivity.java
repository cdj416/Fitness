package com.hongyuan.fitness.ui.person.mine_message;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineMessageBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MineMessageActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的消息");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的消息");

        ActivityMineMessageBinding binding = ActivityMineMessageBinding.bind(mView);
        MineMessageViewModel viewModel = new MineMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
