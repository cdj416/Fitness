package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGetNearbyLocationBinding;

public class GetNearyLocationActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_nearby_location;
    }

    @Override
    protected void initView() {
        setTitle("定位");
        setsetImmersive(0x55000000);
        ActivityGetNearbyLocationBinding binding = ActivityGetNearbyLocationBinding.bind(mView);
        GetNearLocationViwModel viwModel = new GetNearLocationViwModel(this,binding);
        binding.setViewModel(viwModel);
    }
}
