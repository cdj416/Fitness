package com.hongyuan.fitness.ui.find.circle.edit_post;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditPostBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class EditPostActivity extends CustomActivity {
    private ActivityEditPostBinding binding;
    private EditPostViewModel viewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_post;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的");

        binding = ActivityEditPostBinding.bind(mView);
        viewModel = new EditPostViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgVideo.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }
}
