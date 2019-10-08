package com.hongyuan.fitness.ui.find.more_topic;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMoreTopicBinding;

public class MoreTopicActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_topic;
    }

    @Override
    protected void initView() {
        setTitle("更多话题");
        setsetImmersive(0x55000000);
        ActivityMoreTopicBinding binding =ActivityMoreTopicBinding.bind(mView);
        MoreTopicViewModel viewModel = new MoreTopicViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
