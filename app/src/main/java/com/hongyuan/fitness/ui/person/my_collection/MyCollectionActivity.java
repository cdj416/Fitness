package com.hongyuan.fitness.ui.person.my_collection;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyCollectionBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class MyCollectionActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"我的收藏");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"我的收藏");

        ActivityMyCollectionBinding binding =ActivityMyCollectionBinding.bind(mView);
        MyCollectionViewModel viewModel = new MyCollectionViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
