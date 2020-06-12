package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySGoodsDetailBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SgoodsDetailViewModel;

public class SgoodsDetailActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_s_goods_detail;
    }

    @Override
    protected void initView() {

        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivitySGoodsDetailBinding binding = ActivitySGoodsDetailBinding.bind(mView);
        SgoodsDetailViewModel viewModel = new SgoodsDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
