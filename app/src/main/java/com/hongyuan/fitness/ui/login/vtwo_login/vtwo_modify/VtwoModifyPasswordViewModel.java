package com.hongyuan.fitness.ui.login.vtwo_login.vtwo_modify;

import android.view.View;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.base.SingleClick;
import com.hongyuan.fitness.custom_view.InputFieldView;
import com.hongyuan.fitness.databinding.ActivityVtwoModifyPasswordBinding;
import com.hongyuan.fitness.ui.login.MessageCodeBean;
import com.hongyuan.fitness.ui.login.PhoneMessageBean;

public class VtwoModifyPasswordViewModel extends CustomViewModel implements InputFieldView.CodeClick{

    private ActivityVtwoModifyPasswordBinding binding;
    private PhoneMessageBean messageBean;

    public VtwoModifyPasswordViewModel(CustomActivity mActivity, ActivityVtwoModifyPasswordBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        binding.phoneCode.setCodeClick(this);
        //去登录
        binding.goLogin.setOnClickListener(v -> {
            mActivity.finish();
        });
        //修改按钮
        binding.modifySubmit.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                modify();
            }
        });
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
     * 修改密码提交
     * */
    private void modify(){
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
        //由于获取短信token有误，当前接口无法调试
        clearParams().setParams("m_mobile",binding.phoneNum.getText()).setParams("m_pwd",binding.phonePassword.getText())
                .setParams("re_pwd",binding.phonePassword.getText())
                .setParams("dxm",binding.phoneCode.getText());
        Controller.myRequest(ConstantsCode.DO_FIND_PASSWORD,Constants.DO_FIND_PASSWORD,Controller.TYPE_POST,getParams(), ModifyBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof PhoneMessageBean){
            messageBean = (PhoneMessageBean)data;
            showSuccess("验证码发送成功！");
            getMessageCode();
        }
        if(data instanceof MessageCodeBean){
            //showSuccess("验证码发送成功！");
        }

    }

    @Override
    public void onSuccess(int code, Object data) {
        if(code == ConstantsCode.DO_FIND_PASSWORD){
            mActivity.showSuccess("修改成功！");
        }
    }
}
