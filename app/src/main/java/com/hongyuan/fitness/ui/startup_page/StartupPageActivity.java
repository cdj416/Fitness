package com.hongyuan.fitness.ui.startup_page;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityStartupPageBinding;
import com.hongyuan.fitness.util.BaseUtil;

public class StartupPageActivity extends CustomActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup_page;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR6,R.drawable.shape_soid_ffffff,"");
        ActivityStartupPageBinding binding = ActivityStartupPageBinding.bind(mView);
        StartupPageVeiwModel veiwModel = new StartupPageVeiwModel(this,binding);
        binding.setViewModel(veiwModel);

        //h5唤起app页面跳转处理
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String data = uri.getQueryParameter("data");
            //通过EventBus去通知MainActivity显示第三页
            if(BaseUtil.isValue(data)){
                Log.e("cnn","==============拿到了=========="+data);

                Constants.isOpenWeb = data;
            }
        }else{
            Log.e("cnn","==============没拿到==========");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*//h5唤起app页面跳转处理
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            String data = uri.getQueryParameter("data");
            //通过EventBus去通知MainActivity显示第三页
            if(BaseUtil.isValue(data)){
                Log.e("cnn","==============拿到了=========="+data);

                Constants.isOpenWeb = data;
            }
        }else{
            Log.e("cnn","==============没拿到==========");
        }*/
    }
}
