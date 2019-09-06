package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_registerd;

import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.InputFieldView;
import com.hongyuan.fitness.databinding.ActivityVtwoRegisteredBinding;
import com.hongyuan.fitness.ui.login.MessageCodeBean;
import com.hongyuan.fitness.ui.login.PhoneMessageBean;
import com.hongyuan.fitness.ui.person.setting.agreement.AgreementActivity;
import com.hongyuan.fitness.ui.person.setting.privacy_policy.MyWebViewActivity;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.ViewChangeUtil;

public class VtwoRegisterdViewModel extends CustomViewModel implements InputFieldView.CodeClick {

    private ActivityVtwoRegisteredBinding binding;
    private PhoneMessageBean messageBean;

    private boolean isSelect = true;

    public VtwoRegisterdViewModel(CustomActivity mActivity, ActivityVtwoRegisteredBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.phoneCode.setCodeClick(this);
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
        //去登录
        binding.goLogin.setOnClickListener(v -> {
            mActivity.finish();
        });
        //注册按钮
        binding.registerSubmit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                registerd();
            }
        });
    }

    @Override
    public void getClickCode() {
        if(!isValue(binding.phoneNum.getText())){
            onError(1,"请输入手机号码！");
            return;
        }
        //开始倒计时
        binding.phoneCode.startDown();
        clearParams().setParams("m_mobile",binding.phoneNum.getText());
        Controller.myRequest(ConstantsCode.CHECK_REGI_MOBILE, Constants.CHECK_REGI_MOBILE,Controller.TYPE_POST,getParams(), JiaoYanBean.class,this);
    }

    /*
     * 获取短信请求token
     * */
    private void getMessageToken(){
        clearParams().setParams("m_mobile", binding.phoneNum.getText());
        Controller.myRequest(ConstantsCode.GET_MESSAGE_TOKEN,Constants.GET_MESSAGE_TOKEN,Controller.TYPE_POST,getParams(), PhoneMessageBean.class,this);
    }

    /*
     * 发送短信验证码
     * */
    private void sendPhoneCode(){
        clearParams().setParams("m_mobile",binding.phoneNum.getText()).setParams("sms_checked_code",messageBean.getData().getToken());
        Controller.myRequest(ConstantsCode.SEND_REGI_MSG,Constants.SEND_REGI_MSG,Controller.TYPE_POST,getParams(), MessageCodeBean.class,this);
    }

    /*
     * 注册按钮点击
     * */
    private void registerd(){
        if(!isValue(binding.phoneNum.getText())){
            onError(1,"请输入手机号码！");
            return;
        }

        if(!isValue(binding.phoneCode.getText())){
            onError(1,"请输入验证码！");
            return;
        }

        if(!isValue(binding.phonePassword.getText())){
            onError(1,"请输入密码！");
            return;
        }

        if(!isSelect){
            CustomDialog.showMessage(mActivity,"请阅读并同意相关协议!");
            return;
        }

        //由于获取短信token有误，当前接口无法调试
        clearParams().setParams("m_mobile",binding.phoneNum.getText()).setParams("m_pwd",binding.phonePassword.getText())
                .setParams("verification",binding.phoneCode.getText()).setParams("m_from","1");
        Controller.myRequest(Constants.MEMBER_REGISTER,Controller.TYPE_POST,getParams(), RegisterdBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RegisterdBean && isSuccess(data)){
            mActivity.showSuccess("注册成功");
        }
    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.CHECK_REGI_MOBILE){
            getMessageToken();
        }
        if(code == ConstantsCode.GET_MESSAGE_TOKEN){
            messageBean = (PhoneMessageBean)data;
            showSuccess("验证码发送成功！");
            sendPhoneCode();
        }
        if(code == ConstantsCode.SEND_REGI_MSG){
            //showSuccess("验证码发送成功！");
        }
    }
}
