package com.hongyuan.fitness.ui.person.mine_point.point_rules;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRulesBinding;

public class RulesActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rules;
    }

    @Override
    protected void initView() {
        setTitle("积分规则");
        setsetImmersive(0x55000000);
        ActivityRulesBinding binding = ActivityRulesBinding.bind(mView);
        RulesViewModel viewModel = new RulesViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
