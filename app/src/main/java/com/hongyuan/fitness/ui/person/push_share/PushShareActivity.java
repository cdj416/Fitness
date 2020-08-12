package com.hongyuan.fitness.ui.person.push_share;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPushShareBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class PushShareActivity extends CustomActivity {

    private PushShareViewModel viewModel;

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_push_share;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin)){
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"打卡分享");
            getMainTitle().setRightTextColor("分享",getResources().getColor(R.color.color_FF333333));
        }

        if(SkinConstants.SKIN_NAME.BLACK.equals(skin)){
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"打卡分享");
            getMainTitle().setRightTextColor("分享",getResources().getColor(R.color.color_FFFFFF));
        }


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
}
