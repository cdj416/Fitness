package com.hongyuan.fitness.ui.encyclopedia.edit_encyclopedia;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseDataSingle;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEditEncyclopediaBinding;

public class EditEncyclopediaActivity extends CustomActivity {

    private EditEncyclopediaViewModel viewModel;
    private ActivityEditEncyclopediaBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_encyclopedia;
    }

    @Override
    protected void initView() {
        setTitle("发布百科");
        setsetImmersive(0x55000000);
        binding = ActivityEditEncyclopediaBinding.bind(mView);
        viewModel = new EditEncyclopediaViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(BaseDataSingle.getInstance().getBkType() != null){
            viewModel.setChooseType(BaseDataSingle.getInstance().getBkType());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ChooseOneImgView.SINGLE_IMG){
            binding.coverImg.onActivityResult(requestCode,resultCode,data);
        }else{
            binding.imgOrVideo.onActivityResult(requestCode,resultCode,data);
        }
    }
}
