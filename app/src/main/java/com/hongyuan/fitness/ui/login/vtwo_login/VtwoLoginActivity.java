package com.hongyuan.fitness.ui.login.vtwo_login;

import android.view.KeyEvent;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityVtwoLoginBinding;
import com.hongyuan.fitness.ui.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

public class VtwoLoginActivity extends CustomActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_vtwo_login;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityVtwoLoginBinding binding = ActivityVtwoLoginBinding.bind(mView);
        VtwoLoginViewModel viewModel = new VtwoLoginViewModel(this,binding);
        binding.setViewModel(viewModel);
    }

    @Override
    //安卓重写返回键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            //通知首页变换数据及显示页面
            EventBus.getDefault().post(ConstantsCode.EB_START_MAIN,"");
            startActivity(MainActivity.class);
        }
        return true;
    }
}
