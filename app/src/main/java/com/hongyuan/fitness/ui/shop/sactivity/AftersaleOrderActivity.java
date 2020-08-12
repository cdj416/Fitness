package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAftersaleOrderBinding;
import com.hongyuan.fitness.databinding.ActivityBottomAftersaleorderBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.AftersaleOrderViwModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.AftersaleOrderBottomViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class AftersaleOrderActivity extends CustomActivity {

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_aftersaleorder;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aftersale_order;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"订单详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"订单详情");

        ActivityBottomAftersaleorderBinding bottomBing = ActivityBottomAftersaleorderBinding.bind(bottomChildView);
        AftersaleOrderBottomViewModel bottomViewModel = new AftersaleOrderBottomViewModel(this,bottomBing);
        bottomBing.setViewModel(bottomViewModel);

        ActivityAftersaleOrderBinding binding = ActivityAftersaleOrderBinding.bind(mView);
        AftersaleOrderViwModel viwModel = new AftersaleOrderViwModel(this,binding,bottomViewModel);
        binding.setViewModel(viwModel);
    }
}
