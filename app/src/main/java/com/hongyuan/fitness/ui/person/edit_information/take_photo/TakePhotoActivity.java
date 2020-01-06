package com.hongyuan.fitness.ui.person.edit_information.take_photo;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityTakePhotoBinding;

public class TakePhotoActivity extends CustomActivity {

    private ActivityTakePhotoBinding binding;
    private TakePhotoViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"人脸识别");
        binding = ActivityTakePhotoBinding.bind(mView);
        viewModel = new TakePhotoViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestory();
    }
}
