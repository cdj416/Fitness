package com.hongyuan.fitness.ui.find.circle.report;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityReportBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class ReportActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
        setTitleBar(TYPE_BAR5,R.drawable.theme_shape_soid_ffffff,"举报");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.theme_shape_soid_ffffff_black,"举报");

        ActivityReportBinding binding = ActivityReportBinding.bind(mView);
        ReportViewModel viewModel = new ReportViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
