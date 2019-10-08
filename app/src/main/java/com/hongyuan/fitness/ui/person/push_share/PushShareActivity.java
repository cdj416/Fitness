package com.hongyuan.fitness.ui.person.push_share;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPushShareBinding;

public class PushShareActivity extends CustomActivity {

    private PushShareViewModel viewModel;

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_push_share;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"");
        ActivityPushShareBinding binding = ActivityPushShareBinding.bind(mView);
        viewModel = new PushShareViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.dialog_out_anim);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.cancelAnit();
    }
}
