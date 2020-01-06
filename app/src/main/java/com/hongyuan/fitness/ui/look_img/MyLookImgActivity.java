package com.hongyuan.fitness.ui.look_img;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyLookImgBinding;

public class MyLookImgActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_look_img;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_soid_ff000000,"");
        ActivityMyLookImgBinding binding = ActivityMyLookImgBinding.bind(mView);
        MyLookImgViewModel viewModel = new MyLookImgViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

}
