package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityGoodsPromoteBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.PromoteGoodsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class PromoteGoodsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_goods_promote;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"推广专区");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"推广专区");

        AcitivityGoodsPromoteBinding binding = AcitivityGoodsPromoteBinding.bind(mView);
        PromoteGoodsViewModel viewModel = new PromoteGoodsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
