package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.os.Bundle;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
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
import com.hongyuan.fitness.ui.shop.sinterface.ScollChangeLinstener;
import com.hongyuan.fitness.ui.shop.sviewpage.SshopDetailsViewPagerAdapter;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.huanxin.HuanXinUtils;

public class SgoodsDetailViewModel extends CustomViewModel implements GoOtherPageListener, GoodsDetailIntener, ScollChangeLinstener, View.OnClickListener {

    private ActivitySGoodsDetailBinding binding;

    //商品详情数据
    private SgoodsDetailBeans.DataBean.InfoBean infoBean;

    private KeFuBeans.DataBean keFuBeans;

    //记录详情页面状态栏的状态值
    public static final int INDEX_TOP = 0X1;
    public static final int INDEX_CENTER = 0X2;
    public static final int INDEX_BOTTOM = 0X3;
    private int indexType = INDEX_TOP;
    private String mScale;
    //viewpage当前显示的内容下标
    private int mPosition;

    public SgoodsDetailViewModel(CustomActivity mActivity,ActivitySGoodsDetailBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {

        SshopDetailsViewPagerAdapter meunAdapter = new SshopDetailsViewPagerAdapter(mActivity.getSupportFragmentManager(),this,this,this);
        binding.viewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.viewPager);

        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.addOnPageChangeListener(getOnPageChangeListener());


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

        binding.back.setOnClickListener(this);
        binding.tBack.setOnClickListener(this);
        binding.tMessage.setOnClickListener(this);
        binding.message.setOnClickListener(this);
        binding.tCart.setOnClickListener(this);
        binding.cart.setOnClickListener(this);
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

    /*
    * 内容滑动监听改变头部状态效果
    * */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        verticalOffset = -verticalOffset;

        if(mPosition != 0) return;

        if (verticalOffset <= 0) {   //设置标题的背景颜色
            indexType = INDEX_TOP;
            this.mScale = "0";
            changeShow(indexType,mScale);

        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            indexType = INDEX_BOTTOM;
            this.mScale = "1";
            changeShow(indexType,mScale);
        } else {
            String scale = BigDecimalUtils.div(String.valueOf(verticalOffset),String.valueOf(appBarLayout.getTotalScrollRange()),1);
            this.mScale = scale;
            indexType = INDEX_CENTER;
            changeShow(indexType,mScale);
        }
    }

    /*
     * viewPage页面变动监听
     * */
    private ViewPager.OnPageChangeListener getOnPageChangeListener(){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                if(position == 0){
                    changeShow(indexType,mScale);
                }else{
                    changeShow(INDEX_BOTTOM,mScale);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    /*
    * 透明度的变动
    * */
    private void changeShow(int indexType,String scale){
        if(indexType == INDEX_TOP){
            binding.barHeight.setAlpha(0);
            binding.layoutMenu.setAlpha(0);

            binding.tBack.setAlpha(1f);
            binding.tBack.setVisibility(View.VISIBLE);
            binding.back.setAlpha(0f);
            binding.back.setVisibility(View.INVISIBLE);

            binding.tMessage.setAlpha(1f);
            binding.tMessage.setVisibility(View.VISIBLE);
            binding.message.setAlpha(0f);
            binding.message.setVisibility(View.INVISIBLE);

            binding.tCart.setAlpha(1f);
            binding.tCart.setVisibility(View.VISIBLE);
            binding.cart.setAlpha(0f);
            binding.cart.setVisibility(View.INVISIBLE);
        }else if(indexType == INDEX_BOTTOM){
            binding.barHeight.setAlpha(1);
            binding.layoutMenu.setAlpha(1);
            binding.tBack.setAlpha(0f);
            binding.tBack.setVisibility(View.INVISIBLE);
            binding.back.setAlpha(1f);
            binding.back.setVisibility(View.VISIBLE);

            binding.tMessage.setAlpha(0f);
            binding.tMessage.setVisibility(View.INVISIBLE);
            binding.message.setAlpha(1f);
            binding.message.setVisibility(View.VISIBLE);

            binding.tCart.setAlpha(0f);
            binding.tCart.setVisibility(View.INVISIBLE);
            binding.cart.setAlpha(1f);
            binding.cart.setVisibility(View.VISIBLE);
        }else{
            binding.barHeight.setAlpha(Float.parseFloat(scale));
            binding.layoutMenu.setAlpha(Float.parseFloat(scale));

            binding.tBack.setAlpha(1-Float.parseFloat(scale));
            binding.back.setAlpha(Float.parseFloat(scale));

            binding.tMessage.setAlpha(1-Float.parseFloat(scale));
            binding.message.setAlpha(Float.parseFloat(scale));

            binding.tCart.setAlpha(1-Float.parseFloat(scale));
            binding.cart.setAlpha(Float.parseFloat(scale));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tBack:
            case R.id.back:
                mActivity.finish();
                break;

            case R.id.tMessage:
            case R.id.message:
                //startActivity(ShopMessageActivity.class,null);
                startActivity(MineMessageActivity.class,null);
                break;

            case R.id.tCart:
            case R.id.cart:
                startActivity(SCartActivity.class,null);
                break;
        }
    }
}
