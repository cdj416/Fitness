package com.hongyuan.fitness.ui.store.store_list;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStoreListBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class StoreListActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_list;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"门店主页");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"门店主页");

        ActivityStoreListBinding binding = ActivityStoreListBinding.bind(mView);
        StoreListViewModel viewModel = new StoreListViewModel(this,binding);
        binding.setViewModel(viewModel);
    }


}
