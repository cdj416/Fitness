package com.hongyuan.fitness.ui.shop.sviewmodel;

import android.graphics.Paint;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsDetailsBinding;
import com.hongyuan.fitness.ui.shop.sadapter.SDMimgAdapter;
import com.hongyuan.fitness.ui.shop.sbeans.SgoodsDetailBeans;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.CustomDialog;
import com.hongyuan.fitness.util.DensityUtil;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;
import java.util.Arrays;
import java.util.List;

public class IgdViewModel extends CustomViewModel {

    private ActivityIntegralGoodsDetailsBinding binding;

    private SgoodsDetailBeans.DataBean.InfoBean infoBean;
    private SDMimgAdapter imgAdapter;

    public IgdViewModel(CustomActivity mActivity, ActivityIntegralGoodsDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        LinearLayoutManager imgManager = new LinearLayoutManager(mActivity);
        imgManager.setOrientation(RecyclerView.VERTICAL);
        binding.detailsImg.setLayoutManager(imgManager);
        imgAdapter = new SDMimgAdapter(DensityUtil.getScreensWith(mActivity));
        binding.detailsImg.setAdapter(imgAdapter);

        binding.tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        //规格弹框
        binding.specificationBox.setOnClickListener(v -> {
            showSpecification();
        });
    }

    /*
    * 打开规格弹框
    * */
    public void showSpecification(){
        CustomDialog.showGoodsSpecification(mActivity,infoBean,null,this);
    }

    /*
     * 设置顶部banner
     * */
    private void setTopBanner(List<String> bannerList){
        binding.banner.setImages(bannerList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

        }).start();
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams().setParams("g_id",getBundle().getString("g_id"));
        Controller.myRequest(Constants.GET_GOODS_DETAIL_SIX,Controller.TYPE_POST,getParams(), SgoodsDetailBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        mActivity.closeLoading();

        if(data instanceof SgoodsDetailBeans){
            infoBean = ((SgoodsDetailBeans)data).getData().getInfo();

            binding.pointNum.setText(String.valueOf(infoBean.getG_point()));
            binding.goodName.setText(infoBean.getG_name());
            binding.redeemedNum.setText("已兑换"+infoBean.getG_sale_num());
            binding.tvOldPrice.setText("￥"+infoBean.getG_marcket_price());

            if(BaseUtil.isValue(infoBean.getG_price()) && Double.parseDouble(infoBean.getG_price()) > 0){
                binding.priceBox.setVisibility(View.VISIBLE);
                binding.goodPrice.setText(BaseUtil.getNoZoon(infoBean.getG_price()));
            }else{
                binding.priceBox.setVisibility(View.GONE);
            }
            if(infoBean.getSku() != null && infoBean.getSku().size() > 0){
                Glide.with(mActivity).load(infoBean.getG_img()).transition(DrawableTransitionOptions.withCrossFade()).into(binding.normImg);
                binding.normNum.setText("共"+infoBean.getSku().size()+"种规格可选");
            }

            //设置banner数据
            setTopBanner(infoBean.getImgs());

            //详情图片集
            if(BaseUtil.isValue(infoBean.getG_desc()) && !infoBean.getG_desc().contains("</p>")){
                String[] imgAry = infoBean.getG_desc().split(",");
                List<String> imgList = Arrays.asList(imgAry);
                imgAdapter.setNewData(imgList);
            }

        }
    }
}
