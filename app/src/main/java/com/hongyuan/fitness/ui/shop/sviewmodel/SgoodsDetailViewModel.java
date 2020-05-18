package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivitySGoodsDetailBinding;
import com.hongyuan.fitness.ui.main.TokenSingleBean;
import com.hongyuan.fitness.ui.person.mine_message.MineMessageActivity;
import com.hongyuan.fitness.ui.person.mine_message.chat_page.ChatPageActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SCartActivity;
import com.hongyuan.fitness.ui.shop.sactivity.SstoreActivity;
import com.hongyuan.fitness.ui.shop.sbeans.KeFuBeans;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;
import com.hongyuan.fitness.ui.shop.sinterface.GoOtherPageListener;
import com.hongyuan.fitness.ui.shop.sinterface.GoodsDetailIntener;
import com.hongyuan.fitness.ui.shop.sviewpage.SshopDetailsViewPagerAdapter;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

public class SgoodsDetailViewModel extends CustomViewModel implements GoOtherPageListener, GoodsDetailIntener {

    private ActivitySGoodsDetailBinding binding;

    //商品详情数据
    private SgoodsDetailBeans.DataBean.InfoBean infoBean;

    private KeFuBeans.DataBean keFuBeans;

    public SgoodsDetailViewModel(CustomActivity mActivity,ActivitySGoodsDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {


        SshopDetailsViewPagerAdapter meunAdapter = new SshopDetailsViewPagerAdapter(mActivity.getSupportFragmentManager(),this,this);
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(3);


        binding.cart.setOnClickListener(v -> {
            startActivity(SCartActivity.class,null);
        });
        binding.message.setOnClickListener(v -> {
            //startActivity(ShopMessageActivity.class,null);
            startActivity(MineMessageActivity.class,null);
        });

        binding.addCart.setOnClickListener(v -> {
            meunAdapter.showGuiGe();
        });
        binding.goBuy.setOnClickListener(v -> {
            meunAdapter.showGuiGe();
        });

        binding.goStore.setOnClickListener(v -> {
            if(infoBean != null){
                Bundle bundle = new Bundle();
                bundle.putString("store_id",String.valueOf(infoBean.getStore_id()));
                startActivity(SstoreActivity.class,bundle);
            }
        });
        binding.goKeFu.setOnClickListener(v -> {
            if(keFuBeans != null){
                CustomDialog.keFuWay(mActivity, (v1, message) -> {
                    if(v1.getId() == R.id.telNum){
                        CustomDialog.callTel(mActivity, keFuBeans.getInfo().getM_mobile(), new CustomDialog.DialogClick() {
                            @Override
                            public void dialogClick(View v) {
                                callTel(keFuBeans.getInfo().getM_mobile());
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
            }
        });
    }

    private void getKeFu(String store_id){
        clearParams().setParams("type","store").setParams("store_id",store_id);
        Controller.myRequest(Constants.GET_ONLINE_KF,Controller.TYPE_POST,getParams(), KeFuBeans.class,this);
    }


    @Override
    public void onSuccess(Object data) {
        if(data instanceof KeFuBeans){
            keFuBeans = ((KeFuBeans)data).getData();
        }
    }

    @Override
    public void goPage(int pageNum) {
        binding.viewPager.setCurrentItem(pageNum);
    }

    @Override
    public void returenData(SgoodsDetailBeans.DataBean.InfoBean infoBean) {
        this.infoBean = infoBean;
        getKeFu(String.valueOf(infoBean.getStore_id()));
    }
}
