package com.hongyuan.fitness.ui.heat;

import android.os.Bundle;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityHeatBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HeatActivity extends CustomActivity {

    private HeatViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heat;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();
        ActivityHeatBinding binding = ActivityHeatBinding.bind(mView);
        viewModel = new HeatViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    /*
     * 刷新数据
     * */
    @Subscribe(id = ConstantsCode.EB_ADD_FOOD_SUSSESS)
    public void refresh(String message) {
        viewModel.lazyLoad();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
