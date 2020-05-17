package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityAllCitysBinding;

public class AllCitysActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_all_citys;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"选择城市");

        AcitivityAllCitysBinding binding = AcitivityAllCitysBinding.bind(mView);
        AllCitysViwModel viwModel = new AllCitysViwModel(this,binding);
        binding.setViewModel(viwModel);
    }
}
