package com.hongyuan.fitness.ui.out_door.run.run_plan;

import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomFragment;

public class RunOneFragment extends CustomFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_run_one;
    }

    @Override
    public void initView(View mView) {
        setCustomBg(R.color.transparent);
    }

    @Override
    public void onSuccess(Object data) {

    }
}
