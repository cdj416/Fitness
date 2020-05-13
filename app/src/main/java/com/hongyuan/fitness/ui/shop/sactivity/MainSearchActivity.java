package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMainSearchBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.MainSearchViewModel;

public class MainSearchActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_search;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");

        ActivityMainSearchBinding binding = ActivityMainSearchBinding.bind(mView);
        MainSearchViewModel viewModel = new MainSearchViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
