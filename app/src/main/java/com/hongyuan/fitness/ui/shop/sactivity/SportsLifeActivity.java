package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySportsLifeBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SportsLifeViewModel;

public class SportsLifeActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sports_life;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"运动生活");

        ActivitySportsLifeBinding binding = ActivitySportsLifeBinding.bind(mView);
        SportsLifeViewModel viewModel = new SportsLifeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
