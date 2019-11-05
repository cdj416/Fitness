package com.hongyuan.fitness.ui.carousel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCarouselBinding;

public class CarouselActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_carousel;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR7,0,"");
        ActivityCarouselBinding binding = ActivityCarouselBinding.bind(mView);
        CarouseViewModel viewModel = new CarouseViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
