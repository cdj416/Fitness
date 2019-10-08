package com.hongyuan.fitness.ui.find.circle.edit_post;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditPostBinding;

public class EditPostActivity extends CustomActivity {
    private ActivityEditPostBinding binding;
    private EditPostViewModel viewModel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_post;
    }

    @Override
    protected void initView() {
        setTitle("我的");
        setsetImmersive(0x55000000);
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
