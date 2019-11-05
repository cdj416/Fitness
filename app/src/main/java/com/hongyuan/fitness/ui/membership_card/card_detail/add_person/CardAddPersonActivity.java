package com.hongyuan.fitness.ui.membership_card.card_detail.add_person;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCardAddPersonBinding;

public class CardAddPersonActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_card_add_person;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"添加成人");
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
