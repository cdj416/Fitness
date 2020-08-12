package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.AcitivityCustomServerBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.KeFuBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

public class CustomServerViewModel extends CustomViewModel {

    private AcitivityCustomServerBinding binding;

    private KeFuBeans.DataBean keFuBeans;

    public CustomServerViewModel(CustomActivity mActivity, AcitivityCustomServerBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        binding.chatKeFu.setOnClickListener(v -> {
            HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                    ,keFuBeans.getInfo().getM_name(),keFuBeans.getInfo().getMi_head());
            Bundle bundle = new Bundle();
            bundle.putString("title",keFuBeans.getInfo().getM_name());
            bundle.putString("username",keFuBeans.getInfo().getM_mobile());
            bundle.putString("lastMsgId",null);
            startActivity(ChatPageActivity.class,bundle);
        });

        binding.telKeFu.setOnClickListener(v -> {
            CustomDialog.callTel(mActivity, keFuBeans.getInfo().getTel(), v1 -> {
                callTel(keFuBeans.getInfo().getTel());
            });
        });
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("type","pt");
        Controller.myRequest(Constants.GET_ONLINE_KF,Controller.TYPE_POST,getParams(), KeFuBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof KeFuBeans){
            keFuBeans = ((KeFuBeans)data).getData();
        }
    }
}
