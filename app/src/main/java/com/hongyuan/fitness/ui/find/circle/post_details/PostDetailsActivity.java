package com.hongyuan.fitness.ui.find.circle.post_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPostDetailsBinding;

public class PostDetailsActivity extends CustomActivity {
    private PostDetailsViewModel viewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_details;
    }

    @Override
    protected void initView() {
        setTitle("详情");
        setsetImmersive(0x55000000);
        ActivityPostDetailsBinding binding = ActivityPostDetailsBinding.bind(mView);
        viewModel = new PostDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
