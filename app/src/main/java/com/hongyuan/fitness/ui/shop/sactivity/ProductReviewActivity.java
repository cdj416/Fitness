package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityProductReviewBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ProductReviewViewModel;

public class ProductReviewActivity extends CustomActivity {

    private ActivityProductReviewBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_review;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"发表评论");

        binding = ActivityProductReviewBinding.bind(mView);
        ProductReviewViewModel viewModel = new ProductReviewViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgVideo.onActivityResult(requestCode,resultCode,data);
    }
}
