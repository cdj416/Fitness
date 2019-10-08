package com.hongyuan.fitness.ui.find.circle.topic_participant;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityTopicParticipantBinding;

public class TopicParticipantActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_participant;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"话题参与人");
        ActivityTopicParticipantBinding binding = ActivityTopicParticipantBinding.bind(mView);
        TopicParticipantViewModel viewModel = new TopicParticipantViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
