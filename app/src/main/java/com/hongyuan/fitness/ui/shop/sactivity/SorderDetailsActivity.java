package com.hongyuan.fitness.ui.shop.sactivity;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomSorderDetailsBinding;
import com.hongyuan.fitness.databinding.ActivitySOrderDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.SorderDetailViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.SorderBottomViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class SorderDetailsActivity extends CustomActivity {

    private  SorderDetailViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_s_order_details;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_sorder_details;
    }

    @Override
    protected void initView() {

        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"订单详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"订单详情");

        ActivitySOrderDetailsBinding binding = ActivitySOrderDetailsBinding.bind(mView);
        viewModel = new SorderDetailViewModel(this,binding);
        binding.setViewModel(viewModel);

        ActivityBottomSorderDetailsBinding bottomBinding = ActivityBottomSorderDetailsBinding.bind(bottomChildView);
        SorderBottomViewModel bottomViewModel = new SorderBottomViewModel(this,viewModel,bottomBinding);
        bottomBinding.setViewModel(bottomViewModel);
    }

    @Override
    protected void forResult(Bundle bundle) {
        viewModel.forResult(bundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
