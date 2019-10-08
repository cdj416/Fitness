package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGetNearbyLocationBinding;

public class GetNearyLocationActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_get_nearby_location;
    }

    @Override
    protected void initView() {
        setTitle("选择位置");
        setsetImmersive(0x55000000);
        ActivityGetNearbyLocationBinding binding = ActivityGetNearbyLocationBinding.bind(mView);
        GetNearLocationViwModel viwModel = new GetNearLocationViwModel(this,binding);
        binding.setViewModel(viwModel);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.dialog_out_anim);
    }
}
