package com.hongyuan.fitness.ui.carousel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.databinding.ActivityCarouselBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.startup_page.FistUseBean;
import com.hongyuan.fitness.util.GlideImageLoader;
import com.hongyuan.fitness.util.SharedPreferencesUtil;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

public class CarouselActivity extends CustomActivity {

    private ActivityCarouselBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_carousel;
    }

    @Override
    protected void initView() {
        hideTitle(true);
        setImmersive();

        binding = ActivityCarouselBinding.bind(mView);
        setBanner();
    }

    /*
     * 轮播图的设定
     * */
    private void setBanner(){
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.mipmap.banner_one);
        imgList.add(R.mipmap.banner_two);
        imgList.add(R.mipmap.banner_third);
        imgList.add(R.mipmap.banner_fourth);
        binding.carouselBanner.setImages(imgList)
                .setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .isAutoPlay(false)
                .setOnBannerListener(position -> {
                    if(position == 3){
                        FistUseBean fistUseBean = new FistUseBean();
                        fistUseBean.setFirst(true);
                        //标识已经显示过引导页
                        SharedPreferencesUtil.putBean(this,"firstUse",fistUseBean);
                        startActivity(MainActivity.class,null);
                        finish();
                    }
                })
                .start();
    }
}
