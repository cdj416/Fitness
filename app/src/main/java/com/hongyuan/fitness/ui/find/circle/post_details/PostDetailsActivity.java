package com.hongyuan.fitness.ui.find.circle.post_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPostDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class PostDetailsActivity extends CustomActivity {
    private PostDetailsViewModel viewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"详情");

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
