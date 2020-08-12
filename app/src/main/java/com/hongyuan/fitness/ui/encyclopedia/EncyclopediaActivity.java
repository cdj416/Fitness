package com.hongyuan.fitness.ui.encyclopedia;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class EncyclopediaActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encyclopedia;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"健康百科");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"健康百科");

        ActivityEncyclopediaBinding binding =ActivityEncyclopediaBinding.bind(mView);
        EncyclopediaViewModel viewModel = new EncyclopediaViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
