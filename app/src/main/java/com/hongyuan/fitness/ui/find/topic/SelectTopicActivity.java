package com.hongyuan.fitness.ui.find.topic;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityFindTopicBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class SelectTopicActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_topic;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"发现话题");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"发现话题");

        ActivityFindTopicBinding binding = ActivityFindTopicBinding.bind(mView);
        SelectTopicViewModel viewModel = new SelectTopicViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
