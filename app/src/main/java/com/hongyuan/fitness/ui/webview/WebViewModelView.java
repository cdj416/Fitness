package com.hongyuan.fitness.ui.webview;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityWebviewBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

public class WebViewModelView extends CustomViewModel {

    private ActivityWebviewBinding binding;

    public AgentWeb mAgentWeb;

    public WebViewModelView(CustomActivity mActivity, ActivityWebviewBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        //测试使用
        binding.showUrl.setOnClickListener(v -> {
            CustomDialog.showUrl(mActivity,mAgentWeb.getWebCreator().getWebView().getUrl());
        });


        binding.title.setText(getBundle().getString("title",""));
        binding.goBack.setOnClickListener(v -> {
            if (mAgentWeb.getWebCreator().getWebView().canGoBack()) {
                mAgentWeb.getWebCreator().getWebView().goBack();//返回上个页面
                return;
            } else {
                mActivity.finish();
            }

        });
        binding.close.setOnClickListener(v -> {
            mActivity.finish();
        });

        mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(binding.mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb的父控件。
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_EF5B48), 1)//设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK) //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1) //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)//打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .interceptUnkownUrl() //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .createAgentWeb()//创建AgentWeb。
                .ready()//设置 WebSettings。
                .go(getBundle().getString("url")); //WebView载入该url地址的页面并显示。


        AgentWebConfig.debug();

        // AgentWeb 4.0 开始，删除该类以及删除相关的API
        //DefaultMsgConfig.DownloadMsgConfig mDownloadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownloadMsgConfig();
        //  mDownloadMsgConfig.setCancel("放弃");  // 修改下载提示信息，这里可以语言切换

        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        //mAgentWeb.getWebCreator().getWebView()  获取WebView .

		//mAgentWeb.getWebCreator().getWebView().setOnLongClickListener();


        mAgentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
        //webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //优先使用网络
        mAgentWeb.getWebCreator().getWebView().getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //将图片调整到适合webview的大小
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        //支持内容重新布局
        mAgentWeb.getWebCreator().getWebView().getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持自动加载图片
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadsImagesAutomatically(true);
        //当webview调用requestFocus时为webview设置节点
        mAgentWeb.getWebCreator().getWebView().getSettings().setNeedInitialFocus(true);
        //自适应屏幕
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        mAgentWeb.getWebCreator().getWebView().getSettings().setDomStorageEnabled(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setDatabaseEnabled(true);
        //支持缩放
        mAgentWeb.getWebCreator().getWebView().getSettings().setBuiltInZoomControls(false);
        mAgentWeb.getWebCreator().getWebView().getSettings().setSupportZoom(false);

        //初始化js交互对象
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterfaceWeb(mAgentWeb, mActivity, this));

        //允许webview对文件的操作
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccess(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowUniversalAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) {
                mAgentWeb.getWebCreator().getWebView().goBack();//返回上个页面
                return true;
            }
            return mActivity.onKeyDown(keyCode, event);//退出H5界面
        });
    }

    /*
    * 请求保存创建的群组id
    * */
    public void updateGroup(String groupTitle,String gs_id,String group_chat_id){
        this.groupTitle = groupTitle;
        this.group_chat_id = group_chat_id;
        clearParams().setParams("gs_id",gs_id).setParams("group_chat_id",group_chat_id);
        Controller.myRequest(ConstantsCode.GYM_SPORT_GROUP_CHAT_ADD,Constants.GYM_SPORT_GROUP_CHAT_ADD,Controller.TYPE_POST,getParams(), BaseBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {

    }

    //会话标题
    private String groupTitle;
    //会话id
    private String group_chat_id;

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.GYM_SPORT_GROUP_CHAT_ADD){
            HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                    ,"","");
            Bundle bundle = new Bundle();
            bundle.putString("title",groupTitle);
            bundle.putString("username",group_chat_id);
            bundle.putBoolean("isGroup",true);
            bundle.putString("lastMsgId","");
            startActivity(ChatPageActivity.class,bundle);
        }
    }
}
