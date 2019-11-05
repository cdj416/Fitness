package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.InputFieldView;
import com.hongyuan.fitness.databinding.ActivityVtwoVerificationCodeLoginBinding;
import com.hongyuan.fitness.ui.login.LoginBean;
import com.hongyuan.fitness.ui.login.MessageCodeBean;
import com.hongyuan.fitness.ui.login.PhoneMessageBean;
import com.hongyuan.fitness.ui.login.vtwo_login.VtwoLoginActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_modify.VtwoModifyPasswordActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_registerd.VtwoRegisterdActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.out_door.about_you.AboutYouActivity;
import com.hongyuan.fitness.ui.person.setting.agreement.AgreementActivity;
import com.hongyuan.fitness.ui.webview.WebViewActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

public class VtwoVerificationLoginViewModel extends CustomViewModel implements InputFieldView.CodeClick{

    private ActivityVtwoVerificationCodeLoginBinding binding;
    private PhoneMessageBean messageBean;

    private boolean isSelect = true;

    public VtwoVerificationLoginViewModel(CustomActivity mActivity, ActivityVtwoVerificationCodeLoginBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.phoneCode.setCodeClick(this);
        //密码登录
        binding.goPasswordLogin.setOnClickListener(v -> {
            startActivity(VtwoLoginActivity.class,null);
        });
        //修改密码
        binding.goModifyPassword.setOnClickListener(v -> {
            startActivity(VtwoModifyPasswordActivity.class,null);
        });
        //点击同意
        binding.clickAgree.setOnClickListener(v -> {
            if(isSelect){
                isSelect = false;
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.clickAgree, R.mipmap.vtwo_login_xieyi_default_img);
            }else{
                isSelect = true;
                ViewChangeUtil.changeLeftDrawable(mActivity,binding.clickAgree, R.mipmap.vtwo_login_xieyi_select_img);
            }
        });
        //查看用户协议
        binding.userAgreement.setOnClickListener(v -> {
            startActivity(AgreementActivity.class,null);
        });
        //隐私政策
        binding.privacyPolicy.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url","http://www.hongyuangood.com/xy/xy.html");
            bundle.putString("title","隐私政策");
            startActivity(WebViewActivity.class,bundle);
        });
        //去注册
        binding.goRegister.setOnClickListener(v -> {
            startActivity(VtwoRegisterdActivity.class,null);
        });
        //登录按钮
        binding.loginSubmit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                getLogin();
            }
        });
    }

    /*
     * 调用登录接口
     * */
    private void getLogin(){

        if(!isValue(binding.phoneNum.getText())){
            onError(1,"请输入手机号码！");
            return;
        }

        if(!isValue(binding.phoneCode.getText())){
            onError(1,"请输入验证码！");
            return;
        }

        if(!isSelect){
            CustomDialog.showMessage(mActivity,"请阅读并同意相关协议!");
            return;
        }
        mActivity.showLoading();
        clearParams().setParams("m_mobile", binding.phoneNum.getText()).setParams("dxm",binding.phoneCode.getText());
        Controller.myRequest(Constants.MEMBERLOGIN_DXM,Controller.TYPE_POST,getParams(), LoginBean.class,this);
    }

    /*
     * 获取短信验证码
     * */
    private void getMessageCode(){
        clearParams().setParams("m_mobile",binding.phoneNum.getText()).setParams("sms_checked_code",messageBean.getData().getToken());
        Controller.myRequest(Constants.SEND_REGI_MSG,Controller.TYPE_POST,getParams(), MessageCodeBean.class,this);
    }

    /*
     * 先获取短信token
     * */
    @Override
    public void getClickCode() {
        if(!isValue(binding.phoneNum.getText())){
            onError(1,"请输入手机号码！");
            return;
        }
        //开始倒计时
        binding.phoneCode.startDown();
        clearParams().setParams("m_mobile", binding.phoneNum.getText());
        Controller.myRequest(Constants.GET_MESSAGE_TOKEN,Controller.TYPE_POST,getParams(), PhoneMessageBean.class,this);
    }

    /*
     * 检测是否有记录身体指数
     * */
    private void getCheckBody(){
        clearParams();
        Controller.myRequest(ConstantsCode.CHECK_MEMBER_BOBY_INDEX,Constants.CHECK_MEMBER_BOBY_INDEX,Controller.TYPE_POST,getParams(),this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PhoneMessageBean && isSuccess(data)){
            messageBean = (PhoneMessageBean)data;
            showSuccess("验证码发送成功！");
            getMessageCode();
        }
        if(data instanceof MessageCodeBean && isSuccess(data)){
            //showSuccess("验证码发送成功！");
        }

        if(data instanceof LoginBean){
            LoginBean.DataBean login = ((LoginBean) data).getData();
            //存储用户登录信息
            SharedPreferencesUtil.putBean(mActivity,LOGIN_SESSION,login);
            //登录之后去添加登录账户信息
            userToken.setM_id(login.getM_id());
            userToken.setM_mobile(login.getM_mobile());
            userToken.setRole_id(String.valueOf(login.getRole_id()));
            //通过EventBus去通知MainActivity去更新数据
            EventBus.getDefault().postSticky(new MessageEvent(null));
            //mActivity.showSuccess("登录成功", MainActivity.class,null);

            //检测是否录入身体指数
            getCheckBody();
            JPushInterface.setAlias(MyApplication.getInstance(), 100, userToken.getM_mobile());//设置别名或标签
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        mActivity.closeLoading();
        if(code == ConstantsCode.CHECK_MEMBER_BOBY_INDEX){
            if(code == ConstantsCode.CHECK_MEMBER_BOBY_INDEX){
                try {
                    JSONObject object = new JSONObject(data.toString());
                    JSONObject jsonObject = (JSONObject) object.get("data");
                    if(BaseUtil.isJsonValue(jsonObject.get("info"))){
                        //去注册登录环信账号。
                        HuanXinUtils.getInstance().registerdHuanXin(userToken.getM_mobile());
                        mActivity.showSuccess("登录成功", MainActivity.class,null);
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putString("pagType","main");
                        startActivity(AboutYouActivity.class,bundle);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
