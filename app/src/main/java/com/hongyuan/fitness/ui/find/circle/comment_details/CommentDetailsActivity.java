package com.hongyuan.fitness.ui.find.circle.comment_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCommentDetailsBinding;

public class CommentDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_details;
    }

    @Override
    protected void initView() {
        setTitle("评论详情");
        setsetImmersive(0x55000000);
        ActivityCommentDetailsBinding binding = ActivityCommentDetailsBinding.bind(mView);
        CommentDetailsViewModel viewModel = new CommentDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
