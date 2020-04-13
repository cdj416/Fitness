package com.hongyuan.fitness.ui.shop.sviewmodel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIntegralGoodsDetailsBinding;
import com.hongyuan.fitness.util.UseGlideImageLoader;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class IgdViewModel extends CustomViewModel {

    private ActivityIntegralGoodsDetailsBinding binding;

    public IgdViewModel(CustomActivity mActivity, ActivityIntegralGoodsDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        setTopBanner(getBannerList());
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

        }).start();
    }

    @Override
    public void onSuccess(Object data) {

    }
}
