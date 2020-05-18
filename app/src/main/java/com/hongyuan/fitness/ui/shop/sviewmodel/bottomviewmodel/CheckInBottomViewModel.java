package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.os.Bundle;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomCheckinBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.shop.sviewmodel.CheckInShopViewModel;
import com.hongyuan.fitness.ui.webview.WebViewActivity;

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

        binding.goXieYi.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.1667799.com/xy/apply_xy.html");
            bundle.putString("title","开店协议");
            mActivity.startActivity(WebViewActivity.class,bundle);
        });
    }

    @Override
    public void onSuccess(Object data) {

    }
}
