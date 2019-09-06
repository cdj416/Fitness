package com.hongyuan.fitness.ui.person.edit_information.modify_name;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityModifyUserNameBinding;

public class ModifyUserNameViewModel extends CustomViewModel {

    private ActivityModifyUserNameBinding binding;

    public ModifyUserNameViewModel(CustomActivity mActivity, ActivityModifyUserNameBinding binding) {
        super(mActivity);
        this.binding = binding;
    }

    @Override
    public void onSuccess(Object data) {

    }
}
