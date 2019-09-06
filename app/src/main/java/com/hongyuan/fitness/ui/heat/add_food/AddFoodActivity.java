package com.hongyuan.fitness.ui.heat.add_food;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityAddFoodBinding;

public class AddFoodActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_food;
    }

    @Override
    protected void initView() {
        setTypeBar(2,"添加早餐");
        getMainTitle().setRightText("完成");
        ActivityAddFoodBinding binding = ActivityAddFoodBinding.bind(mView);
        AddFoodViewModel viewModel = new AddFoodViewModel(this,binding);
        binding.setViewModel(viewModel);
    }
}
