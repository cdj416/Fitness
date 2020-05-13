package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomCheckinBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.CheckInShopViewModel;

public class CheckInBottomViewModel extends CustomViewModel {

    private ActivityBottomCheckinBinding binding;
    private CheckInShopViewModel contentViewModel;
    public CheckInBottomViewModel(CustomActivity mActivity, CheckInShopViewModel contentViewModel, ActivityBottomCheckinBinding binding) {
        super(mActivity);
        this.binding = binding;
        this.contentViewModel = contentViewModel;
        initView();
    }

    @Override
    protected void initView() {
        if(getBundle().getInt("state") == 1){
            binding.submit.setText("再次提交");
        }

        binding.submit.setOnClickListener(v -> {
            contentViewModel.checkAdmin();
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
