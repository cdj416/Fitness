package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityProductReviewBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ProductReviewViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ProductReviewActivity extends CustomActivity {

    private ActivityProductReviewBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_review;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"发表评论");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"发表评论");

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
