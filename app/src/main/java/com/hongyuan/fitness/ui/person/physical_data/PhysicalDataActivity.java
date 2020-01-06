package com.hongyuan.fitness.ui.person.physical_data;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPersonPhysicalDataBinding;

public class PhysicalDataActivity extends CustomActivity {

    private PhysicalDataViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_physical_data;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"身体数据");
        ActivityPersonPhysicalDataBinding binding = ActivityPersonPhysicalDataBinding.bind(mView);
        viewModel = new PhysicalDataViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        viewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }
}
