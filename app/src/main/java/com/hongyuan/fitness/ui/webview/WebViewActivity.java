package com.hongyuan.fitness.ui.webview;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityWebviewBinding;

public class WebViewActivity extends CustomActivity {

    private WebViewModelView modelView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        setTitleBar(TYPE_BAR3,0,"");
        ActivityWebviewBinding binding = ActivityWebviewBinding.bind(mView);
        modelView = new WebViewModelView(this,binding);
        binding.setViewModel(modelView);
    }

    @Override
    public void onResume() {
        modelView.mAgentWeb.getWebLifeCycle().onResume();//恢复
        super.onResume();
    }

    @Override
    public void onPause() {
        modelView.mAgentWeb.getWebLifeCycle().onPause(); //暂停应用内所有WebView ， 调用mWebView.resumeTimers();/mAgentWeb.getWebLifeCycle().onResume(); 恢复。
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        modelView.mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
