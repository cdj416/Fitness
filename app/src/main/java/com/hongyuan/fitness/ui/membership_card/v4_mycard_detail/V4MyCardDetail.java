package com.hongyuan.fitness.ui.membership_card.v4_mycard_detail;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV4MycardDetailsBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class V4MyCardDetail extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v4_mycard_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"会籍卡详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"会籍卡详情");

        ActivityV4MycardDetailsBinding binding = ActivityV4MycardDetailsBinding.bind(mView);
        V4MyCardDetailViewModel viewModel = new V4MyCardDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
