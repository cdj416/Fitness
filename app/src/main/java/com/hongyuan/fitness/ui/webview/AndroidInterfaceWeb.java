package com.hongyuan.fitness.ui.webview;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.util.GsonUtil;
import com.just.agentweb.AgentWeb;

public class AndroidInterfaceWeb {
    private AgentWeb agent;
    private CustomActivity mActivity;
    private CustomViewModel viewModel;

    public AndroidInterfaceWeb(AgentWeb agent,CustomActivity mActivity, CustomViewModel viewModel) {
        this.agent = agent;
        this.mActivity = mActivity;
        this.viewModel = viewModel;
    }

    @JavascriptInterface
    public void androidShare(String params) {
        Log.e("cdj","======params==="+params);
        ShareUtil.showShare(mActivity);
    }

    @JavascriptInterface
    public String androidGetUserInfo(){
       String jsonStr = GsonUtil.toJsonStr(viewModel.getParams());
        return jsonStr;
    }
}
