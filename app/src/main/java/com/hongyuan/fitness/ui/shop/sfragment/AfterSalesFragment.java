package com.hongyuan.fitness.ui.shop.sfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sbeans.KeFuBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

public class AfterSalesFragment extends CustomFragment {

    private TextView chatKeFu,telKeFu;

    private KeFuBeans.DataBean keFuBeans;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_after_sales;
    }

    @Override
    public void initView(View mView) {
        chatKeFu = mView.findViewById(R.id.chatKeFu);
        telKeFu = mView.findViewById(R.id.telKeFu);

        chatKeFu.setOnClickListener(v -> {
            HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                    ,keFuBeans.getInfo().getM_name(),keFuBeans.getInfo().getMi_head());
            Bundle bundle = new Bundle();
            bundle.putString("title",keFuBeans.getInfo().getM_name());
            bundle.putString("username",keFuBeans.getInfo().getM_mobile());
            bundle.putString("lastMsgId",null);
            startActivity(ChatPageActivity.class,bundle);
        });

        telKeFu.setOnClickListener(v -> {
            CustomDialog.callTel(mActivity, keFuBeans.getInfo().getM_mobile(), v1 -> {
                callTel(keFuBeans.getInfo().getM_mobile());
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
