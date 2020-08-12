package com.hongyuan.fitness.ui.membership_card.card_detail.add_person;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCardAddPersonBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class CardAddPersonActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_card_add_person;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"添加成人");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"添加成人");

        ActivityCardAddPersonBinding binding = ActivityCardAddPersonBinding.bind(mView);
        CardAddPersonViewModel viewModel = new CardAddPersonViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.dialog_out_anim);
    }
}
