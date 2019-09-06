package com.hongyuan.fitness.ui.person.waiting_evaluation.evaluation_order_details;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityOrderEvaluationDetailBinding;

public class EvalutationDetailsViewModel extends CustomViewModel {

    private ActivityOrderEvaluationDetailBinding binding;
    public EvalutationDetailsViewModel(CustomActivity mActivity, ActivityOrderEvaluationDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
