package com.hongyuan.fitness.ui.webview;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.reflect.TypeToken;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.custom_view.share_view.ShareUtil;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.NewOrderActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.GsonUtil;
import com.hongyuan.fitness.util.huanxin.CreateGroupBeans;
import com.hongyuan.fitness.util.huanxin.GotoChatBeans;
import com.hongyuan.fitness.util.huanxin.GotoGroupChatBeans;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;
import com.hyphenate.chat.EMGroup;
import com.just.agentweb.AgentWeb;

public class AndroidInterfaceWeb {
    private AgentWeb agent;
    private CustomActivity mActivity;
    private WebViewModelView viewModel;

    public AndroidInterfaceWeb(AgentWeb agent,CustomActivity mActivity, WebViewModelView viewModel) {
        this.agent = agent;
        this.mActivity = mActivity;
        this.viewModel = viewModel;
    }

    /*
    * 分享params：{'shareTitle':'分享的标题','shareUrl':'图片地址','shareContent':'分享的内容'}
    * */
    @JavascriptInterface
    public void androidShare(String params) {
        ShareUtil.showShare(mActivity);
    }


    /*
    * 获取用户登录后用户的参数
    * */
    @JavascriptInterface
    public String androidGetUserInfo(){
        return GsonUtil.toJsonStr(viewModel.getParams());
    }

    /*
    * 掉用登录
    * */
    @JavascriptInterface
    public void androidLogin() {
        mActivity.startActivity(VtwoVerificationLoginActivity.class,null);
    }

    /*
    * 调用支付：orderId:订单id
     * */
    @JavascriptInterface
    public void androidPay(String orderId){
        WebPayModelUtils payModelUtils = new WebPayModelUtils(mActivity,agent);
        CustomDialog.showPay(mActivity, (v, message) -> {
            if(v.getId() == R.id.alipay){
                payModelUtils.alipayPay(orderId);
            }
            if(v.getId() == R.id.wxPay){
                payModelUtils.wxPay(orderId);
            }
        });
    }

    /*
    * 跳转原生购卡订单列表
    * */
    @JavascriptInterface
    public void androidGoMemberCardList(){
        Bundle bundle = new Bundle();
        bundle.putString("title","会员卡订单");
        bundle.putString("type","o_card");
        mActivity.startActivity(NewOrderActivity.class,bundle);
    }

    /*
    * 创建群组params:{"subject":"群标题","desc":"群描述","sportId":"运动id"};
    * */
    @JavascriptInterface
    public void androidCreateGroup(String params){
        CreateGroupBeans baseBean = GsonUtil.getGson().fromJson(params, new TypeToken<CreateGroupBeans>(){}.getType());

        mActivity.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMGroup emGroup = HuanXinUtils.getInstance().createGroup(baseBean.getSubject(),baseBean.getDesc(),"呦，很不错哦");
                    viewModel.updateGroup(baseBean.getSubject(),baseBean.getSportId(),emGroup.getGroupId());
                }catch (Exception e){
                    Log.e("cdj","=======创建失败========"+e.getMessage());
                    e.printStackTrace();
                }
            }
        });

    }

    /*
    * 群组聊天groupId:群聊id
    * */
    @JavascriptInterface
    public void androidGroupChat(String params){
        GotoGroupChatBeans chatBeans = GsonUtil.getGson().fromJson(params, new TypeToken<GotoGroupChatBeans>(){}.getType());
        HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                ,"","");
        Bundle bundle = new Bundle();
        bundle.putString("title",chatBeans.getSubject());
        bundle.putString("username",chatBeans.getGroupId());
        bundle.putBoolean("isGroup",true);
        bundle.putString("lastMsgId","");
        mActivity.startActivity(ChatPageActivity.class,bundle);
    }

    /*
    * 单聊params：{'friendMobile':'对方手机号','friendNickName':'对方昵称','friendAvatar':'对方头像url'}
    * */
    @JavascriptInterface
    public void androidChat(String params){
        GotoChatBeans chatBeans = GsonUtil.getGson().fromJson(params, new TypeToken<GotoChatBeans>(){}.getType());
        HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                ,chatBeans.getFriendNickName(),chatBeans.getFriendAvatar());
        Bundle bundle = new Bundle();
        bundle.putString("title",chatBeans.getFriendNickName());
        bundle.putString("username",chatBeans.getFriendMobile());
        bundle.putString("lastMsgId",null);
        mActivity.startActivity(ChatPageActivity.class,bundle);
    }

    /*
    * 拨打手机
    * callNumber:要呼叫的电话号码
    * */
    @JavascriptInterface
    public void androidCall(String callNumber){
        CustomDialog.callTel(mActivity, callNumber, v -> {
            viewModel.callTel(callNumber);
        });

    }

    /*
    * 用户是否登录,返回false:表示未登录，true:表示已登录
    * */
    @JavascriptInterface
    public String androidIsLogin(){
        return String.valueOf(BaseUtil.isValue(viewModel.userToken.getM_id()));
    }
}
