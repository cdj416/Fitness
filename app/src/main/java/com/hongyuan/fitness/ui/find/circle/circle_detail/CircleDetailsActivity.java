package com.hongyuan.fitness.ui.find.circle.circle_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCircleDetailsBinding;

public class CircleDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_details;
    }

    @Override
    protected void initView() {
        setTitle("圈子详情");
        setsetImmersive(0x55000000);
        ActivityCircleDetailsBinding binding = ActivityCircleDetailsBinding.bind(mView);
        CircleDetailsViewModel viewModel = new CircleDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
