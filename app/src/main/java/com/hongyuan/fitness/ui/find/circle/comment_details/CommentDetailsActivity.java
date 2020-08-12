package com.hongyuan.fitness.ui.find.circle.comment_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCommentDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CommentDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_details;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"评论详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"评论详情");

        ActivityCommentDetailsBinding binding = ActivityCommentDetailsBinding.bind(mView);
        CommentDetailsViewModel viewModel = new CommentDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
