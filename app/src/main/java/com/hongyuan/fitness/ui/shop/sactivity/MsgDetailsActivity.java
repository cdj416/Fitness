package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMsgDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MsgDetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class MsgDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_details;
    }

    @Override
    protected void initView() {
        String titleName = getBundle().getString("titleName");

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,titleName);
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,titleName);

        ActivityMsgDetailsBinding binding = ActivityMsgDetailsBinding.bind(mView);
        MsgDetailsViewModel viewModel = new MsgDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);

    }
}
