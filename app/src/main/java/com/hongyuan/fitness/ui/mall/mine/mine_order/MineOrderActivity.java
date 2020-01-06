package com.hongyuan.fitness.ui.mall.mine.mine_order;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMineOrderBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

public class MineOrderActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_order;
    }

    @Override
    protected void initView() {
        setTitle("我的订单");
        getMainTitle().showLine();
        setsetImmersive(0x55000000);

        ActivityMineOrderBinding binding = ActivityMineOrderBinding.bind(mView);
        MineOrderViewModel viewModel = new MineOrderViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onBackPressed() {
        startActivity(MainActivity.class,null);
    }
}
