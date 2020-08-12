package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySportsLifeBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SportsLifeViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class SportsLifeActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sports_life;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"运动生活");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"运动生活");

        ActivitySportsLifeBinding binding = ActivitySportsLifeBinding.bind(mView);
        SportsLifeViewModel viewModel = new SportsLifeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
