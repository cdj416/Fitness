package com.hongyuan.fitness.ui.person.edit_information;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditInformationBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class EditInformationActivity extends CustomActivity {

    private EditInformationViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_information;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"编辑资料");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"编辑资料");

        ActivityEditInformationBinding binding = ActivityEditInformationBinding.bind(mView);
        viewModel = new EditInformationViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }

}
