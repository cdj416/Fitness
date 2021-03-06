package com.hongyuan.fitness.ui.login.vtwo_login;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.MessageEvent;
import com.hongyuan.fitness.base.MyApplication;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.databinding.ActivityVtwoLoginBinding;
import com.hongyuan.fitness.ui.login.LoginBean;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_modify.VtwoModifyPasswordActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_registerd.VtwoRegisterdActivity;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.person.setting.agreement.AgreementActivity;
import com.hongyuan.fitness.ui.person.setting.privacy_policy.MyWebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.hongyuan.fitness.util.ViewChangeUtil;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;

public class VtwoLoginViewModel extends CustomViewModel {

    private ActivityVtwoLoginBinding binding;

    private boolean isSelect = true;

    public VtwoLoginViewModel(CustomActivity mActivity, ActivityVtwoLoginBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {

        //验证码登录
        binding.goVerificationLogin.setOnClickListener(v -> {
            startActivity(VtwoVerificationLoginActivity.class,null);
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
            startActivity(MyWebViewActivity.class,null);
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
        if(!isSelect){
            CustomDialog.showMessage(mActivity,"请阅读并同意相关协议@");
            return;
        }

        if(!isValue(binding.phoneNumber.getText())){
            onError(0,"请输入手机号！");
        }
        if(!isValue(binding.passwordNumber.getText())){
            onError(0,"请输入密码！");
        }
        clearParams().setParams("m_mobile", binding.phoneNumber.getText()).setParams("m_pwd",binding.passwordNumber.getText());
        //clearParams().setParams("m_mobile", "18183185173").setParams("m_pwd","cdj123456");
        Controller.myRequest(Constants.MEMBER_LOGIN,Controller.TYPE_POST,getParams(), LoginBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
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
            mActivity.showSuccess("登录成功", MainActivity.class,null);

            JPushInterface.setAlias(MyApplication.getInstance(), 100, userToken.getM_mobile());//设置别名或标签
        }
    }


}
