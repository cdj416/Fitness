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
import com.hongyuan.fitness.base.ConstantsCode;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.custom_view.SubAddNumView;
import com.hongyuan.fitness.databinding.ActivityGoodDetailsBinding;
import com.hongyuan.fitness.ui.about_class.class_success.SuccessClassActivity;
import com.hongyuan.fitness.ui.mall.good_details.skuitem_view.SkutemView;
import com.hongyuan.fitness.ui.mall.good_order_details.GoodOrderDetailsActivity;
import com.hongyuan.fitness.ui.mall.good_order_details.SubmitOrderBean;
import com.hongyuan.fitness.ui.mall.mine.mine_order.mine_order_list.MineOrderBeans;
import com.hongyuan.fitness.ui.promt_success.V3SuccessActivity;
import com.hongyuan.fitness.ui.promt_success.V3SuccessBeans;
import com.hongyuan.fitness.ui.store.more_store.MoreStoreActivity;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.BigDecimalUtils;
import com.hongyuan.fitness.util.CustomDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class GoodDetailsViewModel extends CustomViewModel implements SkutemView.ClickSku, SubAddNumView.NumChange, SkutemView.ClickSkuSubmit {

    private ActivityGoodDetailsBinding binding;
    private GoodDetailsBean detailsBean;

    //取货名店Id
    private String quhuo_osid;

    public void setQuhuo_osid(String quhuo_osid){
        this.quhuo_osid = quhuo_osid;
    }

    public GoodDetailsViewModel(CustomActivity mActivity, ActivityGoodDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        lazyLoad();
        initView();
    }

    @Override
    protected void initView() {
        binding.skuView.setClickSkuSubmit(this);
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
            binding.goodPrice.setText("¥0");
            binding.allPrice.setText("¥0");
        }
        if(detailsBean.getData().getInfo().getG_point() > 0){
            binding.goodPoint.setText(BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_point()));
            binding.allPoint.setText(BaseUtil.getNoZoon(detailsBean.getData().getInfo().getG_point()));
        }else{
            binding.goodPoint.setText("0");
            binding.allPoint.setText("0");
        }

        //免费领取商品
        if(Double.valueOf(detailsBean.getData().getInfo().getG_price()) == 0 && detailsBean.getData().getInfo().getG_point() == 0){
            binding.goNext.setText("免费领取");
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

    /*
     * 免费领取商品生成订单
     * */
    private void creatOrder(){
        if(!BaseUtil.isValue(quhuo_osid)){
            CustomDialog.showMessage(mActivity,"请选择取货门店");
            return;
        }

        clearParams().setParams("gp_id",String.valueOf(detailsBean.getData().getInfo().getGp().get(0).getGp_id())).setParams("op_num",binding.skuView.getNum())
                .setParams("store_id",String.valueOf(detailsBean.getData().getInfo().getStore_id())).setParams("op_quhuo_osid",quhuo_osid);
        Controller.myRequest(Constants.ADD_GOODS_ORDER,Controller.TYPE_POST,getParams(), SubmitOrderBean.class,this);
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
        if(Double.valueOf(detailsBean.getData().getInfo().getG_price()) > 0 || detailsBean.getData().getInfo().getG_point() > 0){
            Bundle bundle = new Bundle();
            //传递需要请求的参数集
            bundle.putSerializable("paramsBean",binding.skuView.getParamsBean());
            //传递需要显示的参数集
            bundle.putSerializable("InfoBean",detailsBean.getData().getInfo());
            startActivity(GoodOrderDetailsActivity.class,bundle);
        }else{
            creatOrder();
        }

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

        if(data instanceof SubmitOrderBean){
            //通知首页免费领取商品的数据
            EventBus.getDefault().post(ConstantsCode.EB_CHAGEE_RECEVER_GOODS,String.valueOf(detailsBean.getData().getInfo().getG_id()));
            //领取成功
            goSuccess();
        }
    }

    /*
     * 三版跳转
     * */
    private void goSuccess(){
        Bundle bundle = new Bundle();
        bundle.putSerializable("successBeans",getSuccessBeans());
        startActivity(V3SuccessActivity.class,bundle);
        mActivity.finish();
    }

    /*
     * 组装订单显示信息
     * */
    private V3SuccessBeans getSuccessBeans(){
        V3SuccessBeans beans = new V3SuccessBeans();

        beans.setTitleText("领取商品");
        beans.setShowText("领取成功");
        beans.setBtn2Text("完成");
        List<V3SuccessBeans.ItemConten> list = new ArrayList<>();

        V3SuccessBeans.ItemConten itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent(detailsBean.getData().getInfo().getG_name());
        itemConten.setItemTitle("商品名:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent("0");
        itemConten.setItemTitle("单价:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent("x1");
        itemConten.setItemTitle("数量:");
        list.add(itemConten);

        itemConten = new V3SuccessBeans.ItemConten();
        itemConten.setContent("¥"+BaseUtil.getNoZoon(binding.allPrice.getText().toString()));
        itemConten.setItemTitle("总价:");
        list.add(itemConten);

        beans.setItemContens(list);


        return beans;
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
        }else{
            binding.allPrice.setText("¥0");
        }
        if(detailsBean.getData().getInfo().getG_point() > 0){
            binding.allPoint.setText(BaseUtil.getNoZoon(BigDecimalUtils.mul(String.valueOf(detailsBean.getData().getInfo().getG_point()),num,2)));
        }else{
            binding.allPoint.setText("0");
        }
    }

    /*
    * 规格确定键的返回
    * */
    @Override
    public void getClickSubmit() {
        //直接领取商品
        creatOrder();
    }
}
