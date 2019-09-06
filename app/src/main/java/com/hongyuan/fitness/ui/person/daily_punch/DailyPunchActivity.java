package com.hongyuan.fitness.ui.person.daily_punch;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityDailyPunchBinding;
import com.hongyuan.fitness.ui.person.mine_point.point_rules.RulesActivity;

public class DailyPunchActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_punch;
    }

    @Override
    protected void initView() {
        setTypeBar("每日打卡",R.drawable.shape_gradient_h_39_4a);
        getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FFFFFF));
        getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(RulesActivity.class,null);
        });

        ActivityDailyPunchBinding binding = ActivityDailyPunchBinding.bind(mView);
        DailyPunchViewModel viewModel = new DailyPunchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
