package com.hongyuan.fitness.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMainBinding;
import com.hongyuan.fitness.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends CustomActivity {
    public ActivityMainBinding binding;
    private MainViewModel viewModel;

    public static MainActivity useActivity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        this.useActivity = this;

        setTitleBar(TYPE_BAR1,R.drawable.shape_soid_ffffff,"");
        binding = ActivityMainBinding.bind(mView);
        viewModel = new MainViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.getLastFragment().onActivityResult(requestCode,resultCode,data);
    }

    /*
     * 刷新数据并显示为第一页
     * */
    @Subscribe(id = ConstantsCode.EB_START_MAIN)
    public void refresh(String message) {
        //情况activity里面的userToken数据
        userToken.clearToken();
        binding.viewPager.setCurrentItem(0);
    }

    /*
     * 刷新免费商品领取数据
     * */
    @Subscribe(id = ConstantsCode.EB_CHAGEE_RECEVER_GOODS)
    public void refreshGoods(String message) {
        viewModel.changeGoods(Integer.valueOf(message));
    }

    /*
     * 控制跳转到指定页面
     * */
    @Subscribe(id = ConstantsCode.EB_START_COURSE)
    public void starCourse(String message) {
        binding.viewPager.setCurrentItem(Integer.valueOf(message));
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

    //安卓重写返回键事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            CustomDialog.promptDialog(this, "确定要退出程序？", "确定", "取消", false, v -> {
                if(v.getId() == R.id.isOk){
                    System.exit(0);
                }
            });
            return false;
        }
        return true;
    }

}
