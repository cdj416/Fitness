package com.hongyuan.fitness.ui.person.newedition.activity;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.AcitivityPriviteCourseOrdersBinding;
import com.hongyuan.fitness.ui.person.newedition.viewmodel.PriviteCourseOrdersViewModel;

public class PriviteCourseOrdersActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_privite_course_orders;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"私教课订单");

        AcitivityPriviteCourseOrdersBinding binding = AcitivityPriviteCourseOrdersBinding.bind(mView);
        PriviteCourseOrdersViewModel viewModel = new PriviteCourseOrdersViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
