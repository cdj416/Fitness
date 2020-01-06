package com.hongyuan.fitness.ui.person.my_promote.promote_record;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromotionRecordBinding;

public class PromotionRecordActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promotion_record;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"推广记录");
        ActivityPromotionRecordBinding binding = ActivityPromotionRecordBinding.bind(mView);
        PromotionRecordViewModel viewModel = new PromotionRecordViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
