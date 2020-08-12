package com.hongyuan.fitness.ui.about_class.coach.show_select_time;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShowSelectTimeBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class ShowSelectTimeActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_select_time;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"教练可约时间");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"教练可约时间");

        ActivityShowSelectTimeBinding binding = ActivityShowSelectTimeBinding.bind(mView);
        ShowSelectTimeViewModel viewModel = new ShowSelectTimeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
