package com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditorialEvaluationBinding;

public class EditorialEvaluationActivity extends CustomActivity {

    private ActivityEditorialEvaluationBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editorial_evaluation;
    }

    @Override
    protected void initView() {
        setTitle("发表评论");
        setsetImmersive(0x55000000);
        binding = ActivityEditorialEvaluationBinding.bind(mView);
        EditorialEvaluationViewModel viewModel = new EditorialEvaluationViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgOrVideo.onActivityResult(requestCode,resultCode,data);
    }
}
