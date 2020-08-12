package com.hongyuan.fitness.ui.find.circle.topic_participant;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityTopicParticipantBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class TopicParticipantActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic_participant;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"话题参与人");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"话题参与人");

        ActivityTopicParticipantBinding binding = ActivityTopicParticipantBinding.bind(mView);
        TopicParticipantViewModel viewModel = new TopicParticipantViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
