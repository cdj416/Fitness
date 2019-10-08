package com.hongyuan.fitness.ui.encyclopedia.encyclopedia_detail;

import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityEncyclopediaTestDetailBinding;

public class EncyclopediaDetailActivity extends CustomActivity {

    private ActivityEncyclopediaTestDetailBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_encyclopedia_test_detail;
    }

    @Override
    protected void initView() {
        setTitle("百科详情");
        setsetImmersive(0x55000000);
        binding = ActivityEncyclopediaTestDetailBinding.bind(mView);
        EncyclopediaDetailViewModel viewModel = new EncyclopediaDetailViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onBackPressed() {
        binding.myVideo.onBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        binding.myVideo.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        binding.myVideo.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.myVideo.onDestroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //binding.myVideo.onConfigurationChanged(newConfig);
    }
}
