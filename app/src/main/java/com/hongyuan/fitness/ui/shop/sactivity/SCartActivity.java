package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomScartBinding;
import com.hongyuan.fitness.databinding.ActivityScartBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ScartViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.ScartBottomViewModel;

public class SCartActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scart;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_scart;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"购物车");
        ActivityBottomScartBinding bottomScartBinding = ActivityBottomScartBinding.bind(bottomChildView);
        ScartBottomViewModel bottomViewModel = new ScartBottomViewModel(this,bottomScartBinding);
        bottomScartBinding.setViewModel(bottomViewModel);

        ActivityScartBinding binding = ActivityScartBinding.bind(mView);
        ScartViewModel viewModel = new ScartViewModel(this,binding,bottomViewModel);
        binding.setViewModel(viewModel);
    }
}
