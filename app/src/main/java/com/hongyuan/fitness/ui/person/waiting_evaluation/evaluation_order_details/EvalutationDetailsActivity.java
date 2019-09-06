package com.hongyuan.fitness.ui.person.waiting_evaluation.evaluation_order_details;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityOrderEvaluationDetailBinding;

public class EvalutationDetailsActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_evaluation_detail;
    }

    @Override
    protected void initView() {
        setTitle("订单详情");
        setsetImmersive(0x55000000);
        ActivityOrderEvaluationDetailBinding binding = ActivityOrderEvaluationDetailBinding.bind(mView);
        EvalutationDetailsViewModel viewModel = new EvalutationDetailsViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
