package com.hongyuan.fitness.ui.person.daily_punch;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityDailyPunchBinding;
import com.hongyuan.fitness.ui.person.mine_point.point_rules.RulesActivity;
import com.hongyuan.fitness.util.SkinConstants;

public class DailyPunchActivity extends CustomActivity {
    private DailyPunchViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_punch;
    }

    @Override
    protected void initView() {
        setTypeBar("每日打卡",R.drawable.only_shape_gradient_h_39_4a);

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FF333333));
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            getMainTitle().setRightTextColor("规则",getResources().getColor(R.color.color_FFFFFF));



        getMainTitle().getRightView().setOnClickListener(v -> {
            startActivity(RulesActivity.class,null);
        });

        ActivityDailyPunchBinding binding = ActivityDailyPunchBinding.bind(mView);
        viewModel = new DailyPunchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
