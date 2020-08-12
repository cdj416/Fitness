package com.hongyuan.fitness.ui.find.circle.get_nearby_location;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityGetNearbyLocationBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class GetNearyLocationActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        overridePendingTransition( R.anim.dialog_in_anim,0);
        return R.layout.activity_get_nearby_location;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"选择位置");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"选择位置");

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
