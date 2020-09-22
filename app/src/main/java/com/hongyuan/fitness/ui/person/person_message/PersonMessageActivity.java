package com.hongyuan.fitness.ui.person.person_message;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityPersonMessageBinding;

public class PersonMessageActivity extends CustomActivity {

    private PersonMessageViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_message;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR2,R.drawable.shape_soid_ffffff,"");

        ActivityPersonMessageBinding binding = ActivityPersonMessageBinding.bind(mView);
        viewModel = new PersonMessageViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.lazyLoad();
    }
}
