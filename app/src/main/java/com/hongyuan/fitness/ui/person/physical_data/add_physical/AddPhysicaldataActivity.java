package com.hongyuan.fitness.ui.person.physical_data.add_physical;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAddPhysicaldataBinding;
import com.hongyuan.fitness.util.SkinConstants;

public class AddPhysicaldataActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_physicaldata;
    }

    @Override
    protected void initView() {
        if(SkinConstants.SKIN_NAME.DEFAULT.equals(skin))
            setTitleBar(TYPE_BAR4,R.drawable.shape_gradient_h_39_4a,"身体数据");
        if(SkinConstants.SKIN_NAME.BLACK.equals(skin))
            setTitleBar(TYPE_BAR9,R.drawable.shape_gradient_h_39_4a_black,"身体数据");

        ActivityAddPhysicaldataBinding binding = ActivityAddPhysicaldataBinding.bind(mView);
        AddPhysicaldataViewModel viewModel = new AddPhysicaldataViewModel(this,binding);
        binding.setViewModel(viewModel);
    }


}
