package com.hongyuan.fitness.ui.main;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityAllCitysBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class AllCitysActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_all_citys;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"选择城市");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"选择城市");



        AcitivityAllCitysBinding binding = AcitivityAllCitysBinding.bind(mView);
        binding.mSideBarView.setPaintColor(skin);

        AllCitysViwModel viwModel = new AllCitysViwModel(this,binding);
        binding.setViewModel(viwModel);
    }
}
