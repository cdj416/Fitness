package com.hongyuan.fitness.ui.shop.sactivity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityNewpoitionTaskdetailsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.NewPoitionTaskdetailsViewModel;
import com.hongyuan.fitness.util.SkinConstants;

public class NewPoitionTaskDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newpoition_taskdetails;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"任务列表");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"任务列表");

        ActivityNewpoitionTaskdetailsBinding binding = ActivityNewpoitionTaskdetailsBinding.bind(mView);
        NewPoitionTaskdetailsViewModel viewModel = new NewPoitionTaskdetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
