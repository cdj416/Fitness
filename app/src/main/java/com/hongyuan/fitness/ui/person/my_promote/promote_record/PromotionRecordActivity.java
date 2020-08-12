package com.hongyuan.fitness.ui.person.my_promote.promote_record;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPromotionRecordBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class PromotionRecordActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_promotion_record;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR3,R.drawable.theme_shape_soid_ffffff,"推广记录");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"推广记录");

        ActivityPromotionRecordBinding binding = ActivityPromotionRecordBinding.bind(mView);
        PromotionRecordViewModel viewModel = new PromotionRecordViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
