package com.hongyuan.fitness.ui.person.mine_point.point_rules;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityRulesBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class RulesActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_rules;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"积分规则");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"积分规则");

        ActivityRulesBinding binding = ActivityRulesBinding.bind(mView);
        RulesViewModel viewModel = new RulesViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
