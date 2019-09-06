package com.hongyuan.fitness.ui.find.circle.report;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityReportBinding;
import com.hongyuan.fitness.util.CustomDialog;

public class ReportViewModel extends CustomViewModel {

    private ActivityReportBinding binding;

    public ReportViewModel(CustomActivity mActivity, ActivityReportBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {
            CustomDialog.promptDialog(mActivity, "确定举报该条内容？", "确定", "取消", false, v1 -> {
                if(v1.getId() == R.id.isOk){
                    mActivity.showSuccess("举报成功！");
                }
            });
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
