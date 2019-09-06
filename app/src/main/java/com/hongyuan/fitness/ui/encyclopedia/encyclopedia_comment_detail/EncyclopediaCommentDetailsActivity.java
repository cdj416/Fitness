package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_comment_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaCommentDetailsBinding;

public class EncyclopediaCommentDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encyclopedia_comment_details;
    }

    @Override
    protected void initView() {
        setTitle("评论详情");
        setsetImmersive(0x55000000);
        ActivityEncyclopediaCommentDetailsBinding binding = ActivityEncyclopediaCommentDetailsBinding.bind(mView);
        EncyclopediaCommentDetailsViewModel viewModel = new EncyclopediaCommentDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
