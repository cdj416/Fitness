package com.hongyuan.fitness.ui.store.store_page_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class StoreActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_store;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"门店列表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"门店列表");

        ActivityStoreBinding binding = ActivityStoreBinding.bind(mView);
        StoreViewModel viewModel = new StoreViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
