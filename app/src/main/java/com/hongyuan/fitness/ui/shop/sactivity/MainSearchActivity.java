package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMainSearchBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MainSearchViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class MainSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_search;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
        setTitleBar(TYPE_BAR1,R.drawable.theme_shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR8,R.drawable.theme_shape_soid_ffffff_black,"");

        ActivityMainSearchBinding binding = ActivityMainSearchBinding.bind(mView);
        MainSearchViewModel viewModel = new MainSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
