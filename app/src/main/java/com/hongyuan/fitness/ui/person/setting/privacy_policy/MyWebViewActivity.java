package com.hongyuan.fitness.ui.person.setting.privacy_policy;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityMyWebviewBinding;

public class MyWebViewActivity extends CustomActivity {

    private ActivityMyWebviewBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_webview;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,R.drawable.shape_soid_ffffff,"隐私政策");
        binding = ActivityMyWebviewBinding.bind(mView);
        MyWebViewModelView modelView = new MyWebViewModelView(this,binding);
        binding.setViewModel(modelView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.webView.destroy();
    }
}
