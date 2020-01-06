package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAddPhysicaldataBinding;

public class AddPhysicaldataActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_physicaldata;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"身体数据");
        ActivityAddPhysicaldataBinding binding = ActivityAddPhysicaldataBinding.bind(mView);
        AddPhysicaldataViewModel viewModel = new AddPhysicaldataViewModel(this,binding);
        binding.setViewModel(viewModel);
    }


}
