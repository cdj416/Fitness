package com.hongyuan.fitness.ui.person.waiting_evaluation;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWaitEvaluationBinding;

public class WaitingEvaluationActivity extends CustomActivity {

    private WaitingEvaluationViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wait_evaluation;
    }

    @Override
    protected void initView() {
        setTitle("待评价");
        setsetImmersive(0x55000000);
        ActivityWaitEvaluationBinding binding = ActivityWaitEvaluationBinding.bind(mView);
        viewModel = new WaitingEvaluationViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
