package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.BaseBean;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCollectCouponsBinding;
import com.hongyuan.fitness.ui.shop.sbeans.FirstCategoryBeans;
import com.hongyuan.fitness.ui.shop.sviewpage.SccouponsPagerAdapter;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class CollectCouponsViewModel extends CustomViewModel {

    private ActivityCollectCouponsBinding binding;

    private SccouponsPagerAdapter pageAdapter;

    //分类数据
    private FirstCategoryBeans.DataBean dataBean;

    public CollectCouponsViewModel(CustomActivity mActivity,ActivityCollectCouponsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        setTopBanner(getBannerList());

        pageAdapter = new SccouponsPagerAdapter(mActivity.getSupportFragmentManager());
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

    /*
     * 获取banner本地数据
     * */
    private List<Integer> getBannerList(){
        List<Integer> bList = new ArrayList<>();
        bList.add(R.drawable.banner_test1);
        bList.add(R.drawable.banner_test2);
        bList.add(R.drawable.banner_test3);
        bList.add(R.drawable.banner_test4);
        return bList;
    }

    @Override
    protected void lazyLoad() {
        mActivity.showLoading();
        clearParams();
        Controller.myRequest(Constants.GET_FIRST_CATEGORY,Controller.TYPE_POST,getParams(), FirstCategoryBeans.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        mActivity.closeLoading();
        if(data instanceof FirstCategoryBeans){
            dataBean = ((FirstCategoryBeans)data).getData();

            binding.mViewPager.setOffscreenPageLimit(dataBean.getList().size());
            pageAdapter.setData(dataBean.getList());

        }
    }
}
