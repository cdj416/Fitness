package com.hongyuan.fitness.ui.find.circle.report;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityReportBinding;

public class ReportActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"举报");
        ActivityReportBinding binding = ActivityReportBinding.bind(mView);
        ReportViewModel viewModel = new ReportViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
