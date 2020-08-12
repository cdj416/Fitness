package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomEditextLogisticsBinding;
import com.hongyuan.fitness.ui.shop.sviewmodel.EdLogisticsViewModel;

public class BottomEditextLogisticsViewModel extends CustomViewModel {

    private ActivityBottomEditextLogisticsBinding binding;
    private EdLogisticsViewModel viewModel;

    public BottomEditextLogisticsViewModel(CustomActivity mActivity, ActivityBottomEditextLogisticsBinding binding, EdLogisticsViewModel viewModel) {
        super(mActivity);
        this.binding = binding;
        this.viewModel = viewModel;

        initView();
    }

    @Override
    protected void initView() {
        binding.submit.setOnClickListener(v -> {

            if(viewModel.isUpdateImg()){
                viewModel.updataFile();
            }else{
                viewModel.delGoods("");
            }
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
