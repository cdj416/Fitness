package com.hongyuan.fitness.ui.about_class.class_failure;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityFailureBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class FailureViewModel extends CustomViewModel {

    private ActivityFailureBinding binding;

    public FailureViewModel(CustomActivity mActivity, ActivityFailureBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }


    @Override
    protected void initView() {
        mActivity.getMainTitle().setCentreText(getBundle().getString("titleName"));
        binding.showText.setText(getBundle().getString("failureText"));
    }

    //返回首页
    public BindingCommand goHome = new BindingCommand(() -> {
        startActivity(MainActivity.class,null);
    });
    //返回首页
    public BindingCommand goAgain = new BindingCommand(() -> {
        mActivity.finish();
    });

    @Override
    public void onSuccess(Object data) {

    }
}
