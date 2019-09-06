package com.hongyuan.fitness.ui.person.setting.privacy_policy;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityMyWebviewBinding;

public class MyWebViewModelView extends CustomViewModel {

    private ActivityMyWebviewBinding binding;

    public MyWebViewModelView(CustomActivity mActivity, ActivityMyWebviewBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        binding.webView.loadUrl("http://www.hongyuangood.com/xy/xy.html");
        //WebViewCacheInterceptorInst.getInstance().loadUrl(binding.webView, "http://www.hongyuangood.com/xy/xy.html");
        //clearParams().setParams("img_code","app_xy");
        //Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
    }
}
