package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityIncomeFormBinding;
import com.hongyuan.fitness.ui.shop.sactivity.ShopMessageActivity;

public class IncomeFormViewModel extends CustomViewModel {

    private AcitivityIncomeFormBinding binding;

    public IncomeFormViewModel(CustomActivity mActivity,AcitivityIncomeFormBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        mActivity.getMainTitle().setRightImage(R.mipmap.person_message_mark)
                .setOnClickListener(v -> startActivity(ShopMessageActivity.class,null));
    }

    @Override
    public void onSuccess(Object data) {

    }
}
