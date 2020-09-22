package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityPlatformCouponsBinding;
import com.hongyuan.fitness.ui.shop.sbeans.PlatformCouponsTitleBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.PlatformPagerAdapter;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.List;

public class PlatformCouponsViewModel extends CustomViewModel {

    private ActivityPlatformCouponsBinding binding;

    private PlatformPagerAdapter pageAdapter;

    //分类数据
    private List<PlatformCouponsTitleBeans.DataBean> mList;

    public PlatformCouponsViewModel(CustomActivity mActivity, ActivityPlatformCouponsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {

        pageAdapter = new PlatformPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(pageAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

    }

    /*
     * 设置顶部banner
     * */
    private void setTopBanner(List<Integer> bannerList){
        binding.banner.setImages(bannerList)
                .setImageLoader(new UseGlideImageLoader())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR )
                .setIndicatorGravity(BannerConfig.CENTER).setOnBannerListener(position -> {

            /*JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
            jumpBeans.setImg_href_type(bannerList.get(position).getImg_href_type());
            jumpBeans.setHref_code(bannerList.get(position).getImg_href_code());
            jumpBeans.setHref_id(String.valueOf(bannerList.get(position).getImg_href_id()));

            JumpUtils.goAtherPage(mActivity,jumpBeans);*/
        }).start();
    }


    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.COUPON_FOR_LIST,Controller.TYPE_POST,getParams(), PlatformCouponsTitleBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof PlatformCouponsTitleBeans){
            mList = ((PlatformCouponsTitleBeans)data).getData();

            binding.mViewPager.setOffscreenPageLimit(mList.size());
            pageAdapter.setData(mList);

        }
    }
}
