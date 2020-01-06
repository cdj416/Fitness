package com.hongyuan.fitness.ui.person.physical_data.silhouette;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivitySilhouetteBinding;

public class SihouetteActivity extends CustomActivity {

    private SihouetteViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_silhouette;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"全部剪影");
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
