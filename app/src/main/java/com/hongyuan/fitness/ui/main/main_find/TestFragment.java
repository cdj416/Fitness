package com.hongyuan.fitness.ui.main.main_find;

import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

public class TestFragment extends CustomFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView(View mView) {
        setPromtView(SHOW_EMPTY);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
