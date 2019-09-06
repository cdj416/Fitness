package com.hongyuan.fitness.ui.about_class.coach.show_select_time;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShowSelectTimeBinding;

public class ShowSelectTimeActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_select_time;
    }

    @Override
    protected void initView() {
        setTitle("教练可约时间");
        setsetImmersive(0x55000000);
        ActivityShowSelectTimeBinding binding = ActivityShowSelectTimeBinding.bind(mView);
        ShowSelectTimeViewModel viewModel = new ShowSelectTimeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
