package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityBottomReturnPriceDetailsBinding;
import com.hongyuan.fitness.databinding.ActivityReturnPriceDetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.ReturnPriceDetailsViewModel;
import com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel.BottomReturnPriceDetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class ReturnPriceDetailsActivity extends CustomActivity {

    private ReturnPriceDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_return_price_details;
    }

    @Override
    public int getBottomLayoutId() {
        return R.layout.activity_bottom_return_price_details;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"退款详情");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"退款详情");

        ActivityBottomReturnPriceDetailsBinding bottomBinding = ActivityBottomReturnPriceDetailsBinding.bind(bottomChildView);
        BottomReturnPriceDetailsViewModel bottomViewModel = new BottomReturnPriceDetailsViewModel(this,bottomBinding);
        bottomBinding.setViewModel(bottomViewModel);

        ActivityReturnPriceDetailsBinding binding = ActivityReturnPriceDetailsBinding.bind(mView);
        viewModel = new ReturnPriceDetailsViewModel(this,binding,bottomViewModel);
        binding.setViewModel(viewModel);

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshData();
    }
}
