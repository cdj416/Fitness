package com.hongyuan.fitness.ui.shop.sactivity;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomEditextLogisticsBinding;
import com.hongyuan.fitness.databinding.ActivityEditextLogisticsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.EdLogisticsViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.BottomEditextLogisticsViewModel;

public class EdLogisticsActivity extends CustomActivity {

    private ActivityEditextLogisticsBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_editext_logistics;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_editext_logistics;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"退货物流信息");

        binding = ActivityEditextLogisticsBinding.bind(mView);
        EdLogisticsViewModel viewModel = new EdLogisticsViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomEditextLogisticsBinding bottomBinding = ActivityBottomEditextLogisticsBinding.bind(bottomChildView);
        BottomEditextLogisticsViewModel bottomViewModel = new BottomEditextLogisticsViewModel(this,bottomBinding,viewModel);
        bottomBinding.setViewModel(bottomViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.imgVideo.onActivityResult(requestCode,resultCode,data);
    }
}
