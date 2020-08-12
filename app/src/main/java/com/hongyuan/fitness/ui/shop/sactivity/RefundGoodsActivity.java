package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.Intent;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityRefundGoodsBinding;
import com.hongyuan.fitness.databinding.ActivityBottomRefundGoodsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.RefundGoodsViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.BottomRefundGoodsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class RefundGoodsActivity extends CustomActivity {

    private AcitivityRefundGoodsBinding binding;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.acitivity_refund_goods;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_refund_goods;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我要退货退款");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我要退货退款");

        binding = AcitivityRefundGoodsBinding.bind(mView);
        RefundGoodsViewModel viewModel = new RefundGoodsViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomRefundGoodsBinding binding1 = ActivityBottomRefundGoodsBinding.bind(bottomChildView);
        BottomRefundGoodsViewModel bottomViewModel = new BottomRefundGoodsViewModel(this,binding1,viewModel);
        binding1.setViewModel(bottomViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgVideo.onActivityResult(requestCode,resultCode,data);
    }
}
