package com.hongyuan.fitness.ui.person.my_fan;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFansBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MyFansActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的粉丝");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的粉丝");

        ActivityFansBinding binding = ActivityFansBinding.bind(mView);
        MyFansViewModel viewModel = new MyFansViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
