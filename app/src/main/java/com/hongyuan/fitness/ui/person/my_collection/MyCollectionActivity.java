package com.hongyuan.fitness.ui.person.my_collection;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyCollectionBinding;

public class MyCollectionActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"我的收藏");
        ActivityMyCollectionBinding binding =ActivityMyCollectionBinding.bind(mView);
        MyCollectionViewModel viewModel = new MyCollectionViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
