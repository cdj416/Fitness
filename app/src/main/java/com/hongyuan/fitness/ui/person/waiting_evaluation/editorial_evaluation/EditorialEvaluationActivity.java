package com.hongyuan.fitness.ui.person.waiting_evaluation.editorial_evaluation;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditorialEvaluationBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class EditorialEvaluationActivity extends CustomActivity {

    private ActivityEditorialEvaluationBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editorial_evaluation;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"发表评论");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"发表评论");
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
