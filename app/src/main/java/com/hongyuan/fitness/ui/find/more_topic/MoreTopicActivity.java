package com.hongyuan.fitness.ui.find.more_topic;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMoreTopicBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MoreTopicActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_topic;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"更多话题");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"更多话题");

        ActivityMoreTopicBinding binding =ActivityMoreTopicBinding.bind(mView);
        MoreTopicViewModel viewModel = new MoreTopicViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
