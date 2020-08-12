package com.hongyuan.fitness.ui.shop.sviewmodel.bottomviewmodel;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityBottomReturnPriceDetailsBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.CustomServerActivity;
import com.hongyuan.fitness.ui.shop.sbeans.KeFuBeans;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

public class BottomReturnPriceDetailsViewModel extends CustomViewModel {

    private ActivityBottomReturnPriceDetailsBinding binding;

    private KeFuBeans.DataBean keFuBeans;

    public BottomReturnPriceDetailsViewModel(CustomActivity mActivity, ActivityBottomReturnPriceDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;

        initView();
    }

    @Override
    protected void initView() {
        binding.goSuiDong.setOnClickListener(v -> {
            startActivity(CustomServerActivity.class,null);
        });

        binding.goShop.setOnClickListener(v -> {
            CustomDialog.keFuWay(mActivity, (v1, message) -> {
                if(v1.getId() == R.id.telNum){
                    CustomDialog.callTel(mActivity, keFuBeans.getInfo().getTel(), new CustomDialog.DialogClick() {
                        @Override
                        public void dialogClick(View v) {
                            callTel(keFuBeans.getInfo().getTel());
                        }
                    });
                }

                if(v1.getId() == R.id.goChat){
                    HuanXinUtils.getInstance().setBaseData(TokenSingleBean.getInstance().getM_mobile(),TokenSingleBean.getInstance().getHeadUrl()
                            ,keFuBeans.getInfo().getM_name(),keFuBeans.getInfo().getMi_head());
                    Bundle bundle = new Bundle();
                    bundle.putString("title",keFuBeans.getInfo().getM_name());
                    bundle.putString("username",keFuBeans.getInfo().getM_mobile());
                    bundle.putString("lastMsgId",null);
                    startActivity(ChatPageActivity.class,bundle);
                }
            });
        });
    }

    public void getKeFu(String store_id){
        clearParams().setParams("type","store").setParams("store_id",store_id);
        Controller.myRequest(Constants.GET_ONLINE_KF,Controller.TYPE_POST,getParams(), KeFuBeans.class,this);
    }
    

    @Override
    public void onSuccess(Object data) {
        if(data instanceof KeFuBeans){
            keFuBeans = ((KeFuBeans)data).getData();
        }
    }
}
