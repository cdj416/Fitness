package com.hongyuan.fitness.ui.person.my_promote;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromotionCodeBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class PromotionCodeActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promotion_code;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"推广码");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"推广码");

        ActivityPromotionCodeBinding binding = ActivityPromotionCodeBinding.bind(mView);
        PromotionCodeViewModel viewModel = new PromotionCodeViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
