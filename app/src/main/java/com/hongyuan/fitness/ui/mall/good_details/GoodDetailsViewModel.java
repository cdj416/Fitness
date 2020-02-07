package com.hongyuan.fitness.ui.mall.good_details;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.SubAddNumView;
import com.hongyuan.fitness.databinding.ActivityGoodDetailsBinding;
import com.hongyuan.fitness.ui.mall.good_details.skuitem_view.SkutemView;
import com.hongyuan.fitness.ui.mall.good_order_details.GoodOrderDetailsActivity;
import com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list.MineOrderBeans;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;

import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GoodDetailsViewModel extends CustomViewModel implements SkutemView.ClickSku, SubAddNumView.NumChange {

    private ActivityGoodDetailsBinding binding;
    private GoodDetailsBean detailsBean;

    public GoodDetailsViewModel(CustomActivity mActivity, ActivityGoodDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
        initView();
    }

    @Override
    protected void initView() {
        //mActivity.getMainTitle().getRightView().setOnClickListener(v -> startActivity(MineOrderBeans.class,null));
    }

    @Override
    protected void lazyLoad() {
        clearParams().setParams("g_id",getBundle().getString("g_id"));
        Controller.myRequest(Constants.GET_GOODS_DETAIL,Controller.TYPE_POST,getParams(), GoodDetailsBean.class,this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void setData() {
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhengfangxing_default_img).error(R.mipmap.zhengfangxing_default_img);
        Glide.with(mActivity).load(detailsBean.getData().getInfo().getG_img()).apply(options).into(binding.goodImg);

        binding.goodName.setText(detailsBean.getData().getInfo().getG_name());
        binding.goodIntroduction.setText(detailsBean.getData().getInfo().getG_second_name());

        if(Double.valueOf(detailsBean.getData().getInfo().getG_price()) > 0){
            binding.goodPrice.setText("¥"+BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_price()));
            binding.allPrice.setText("¥"+BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_price()));
        }else{
            binding.goodPrice.setVisibility(View.GONE);
            binding.allPriceBox.setVisibility(View.GONE);
        }
        if(detailsBean.getData().getInfo().getG_point() > 0){
            binding.goodPoint.setText(BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_point()));
            binding.allPoint.setText(BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_point()));
        }else{
            binding.pointBox.setVisibility(View.GONE);
            binding.allPointBox.setVisibility(View.GONE);
        }

        //设置规格数据
        binding.skuView.setSkuData(detailsBean.getData().getInfo(),this,this);

        //初始化webview
        binding.webView.loadDataWithBaseURL(null,detailsBean.getData().getInfo().getG_desc(),"text/html","UTF-8",null);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setLoadsImagesAutomatically(true);
        binding.webView.requestFocus();
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });
    }

    //去支付生产购买订单
    public BindingCommand goNext = new BindingCommand(() -> {

        if(!binding.skuView.getCensor()){
            onError(0,binding.skuView.getErrText());
            return;
        }
        if(!binding.skuView.isHaveStock()){
            onError(0,"抱歉，没有库存了！");
            return;
        }
        Bundle bundle = new Bundle();
        //传递需要请求的参数集
        bundle.putSerializable("paramsBean",binding.skuView.getParamsBean());
        //传递需要显示的参数集
        bundle.putSerializable("InfoBean",detailsBean.getData().getInfo());
        startActivity(GoodOrderDetailsActivity.class,bundle);
    });
    //选择门店
    public BindingCommand goStore = new BindingCommand(() -> {
        startActivity(MoreStoreActivity.class,null);
    });

    @Override
    public void onSuccess(Object data) {
        if(data instanceof GoodDetailsBean){
            detailsBean = (GoodDetailsBean)data;
            setData();
        }
        if(data instanceof GoodSelectSkuBean){
            GoodSelectSkuBean selectSkuBean = (GoodSelectSkuBean)data;
            //设置规格数据
            binding.skuView.setShowData(selectSkuBean.getData().getItem().getGp_stock(),
                    selectSkuBean.getData().getItem().getGp_price(),
                    selectSkuBean.getData().getItem().getGp_point());

            //设置gp_id;
            binding.skuView.setGp_id(String.valueOf(selectSkuBean.getData().getItem().getGp_id()));
        }
    }

    /*
    * sku点击回调(请求点击后的数据)
    * */
    @Override
    public void getClickData(List<GoodDetailsBean.DataBean.InfoBean.SkuBean> selectList) {

        clearParams().setParams("g_id",String.valueOf(detailsBean.getData().getInfo().getG_id()))
                .setParams("second_category_id",String.valueOf(detailsBean.getData().getInfo().getSecond_category_id()));
        for(GoodDetailsBean.DataBean.InfoBean.SkuBean skuBean: selectList){
            if(skuBean.getSelectChildName() != null){
                setParams("sku_name_"+skuBean.getSku_type_id(),skuBean.getSelectChildName());
            }
        }
        Controller.myRequest(Constants.SELECT_SKU,Controller.TYPE_POST,getParams(), GoodSelectSkuBean.class,this);
    }

    /*
    * 随数量改变去改变总价和积分的显示
    * */
    @Override
    public void changeNum(String num) {
        if(Double.valueOf(detailsBean.getData().getInfo().getG_price()) > 0){
            binding.allPrice.setText("¥"+BaseUtil.getNoZoon(BigDecimalUtils.mul(detailsBean.getData().getInfo().getG_price(),num,2)));
        }
        if(detailsBean.getData().getInfo().getG_point() > 0){
            binding.allPoint.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(String.valueOf(detailsBean.getData().getInfo().getG_point()),num,2)));
        }
    }
}
