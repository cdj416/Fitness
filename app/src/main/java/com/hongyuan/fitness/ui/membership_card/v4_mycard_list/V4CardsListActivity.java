package com.hongyuan.fitness.ui.membership_card.v4_mycard_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityV4CardListBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class V4CardsListActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_v4_card_list;
    }

    @Override
    protected void initView() {
        //setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR1,R.drawable.theme_shape_soid_ffffff,"");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR8,R.drawable.theme_shape_soid_ffffff_black,"");

        ActivityV4CardListBinding binding = ActivityV4CardListBinding.bind(mView);
        V4CardsListViewModel viewModel = new V4CardsListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
