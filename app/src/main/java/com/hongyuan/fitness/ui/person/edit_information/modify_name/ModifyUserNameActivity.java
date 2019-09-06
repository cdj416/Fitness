package com.hongyuan.fitness.ui.person.edit_information.modify_name;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityModifyUserNameBinding;

public class ModifyUserNameActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_user_name;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR5,R.drawable.shape_soid_ffffff,"修改名字");
        ActivityModifyUserNameBinding binding = ActivityModifyUserNameBinding.bind(mView);
        ModifyUserNameViewModel viewModel = new ModifyUserNameViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
