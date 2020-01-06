package com.hongyuan.fitness.ui.show_big_img;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityShowBigImgBinding;

public class ShowBigImgActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_big_img;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"");
        ActivityShowBigImgBinding binding = ActivityShowBigImgBinding.bind(mView);
        ShowBigImgViewModel viewModel = new ShowBigImgViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
