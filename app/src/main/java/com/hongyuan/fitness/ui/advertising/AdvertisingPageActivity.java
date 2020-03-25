package com.hongyuan.fitness.ui.advertising;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAdvertisingPageBinding;

public class AdvertisingPageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advertising_page;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityAdvertisingPageBinding binding = ActivityAdvertisingPageBinding.bind(mView);
        AdvertisingPageViewModel viewModel = new AdvertisingPageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
