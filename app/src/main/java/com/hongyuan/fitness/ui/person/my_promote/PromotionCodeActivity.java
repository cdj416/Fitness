package com.hongyuan.fitness.ui.person.my_promote;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromotionCodeBinding;

public class PromotionCodeActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promotion_code;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"推广码");

        ActivityPromotionCodeBinding binding = ActivityPromotionCodeBinding.bind(mView);
        PromotionCodeViewModel viewModel = new PromotionCodeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
