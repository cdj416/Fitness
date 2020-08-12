package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySilhouetteBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class SihouetteActivity extends CustomActivity {

    private SihouetteViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_silhouette;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"全部剪影");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"全部剪影");

        ActivitySilhouetteBinding binding = ActivitySilhouetteBinding.bind(mView);
        viewModel = new SihouetteViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
