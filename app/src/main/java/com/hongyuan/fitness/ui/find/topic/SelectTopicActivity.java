package com.hongyuan.fitness.ui.find.topic;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFindTopicBinding;

public class SelectTopicActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_topic;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"发现话题");
        ActivityFindTopicBinding binding = ActivityFindTopicBinding.bind(mView);
        SelectTopicViewModel viewModel = new SelectTopicViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
