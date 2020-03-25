package com.hongyuan.fitness.ui.carousel;

import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.Constants;
import com.hongyuan.fitness.base.Controller;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityCarouselBinding;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;

import java.util.ArrayList;
import java.util.List;

public class CarouseViewModel extends CustomViewModel {
    private ActivityCarouselBinding binding;

    private CarousePagerAdapter meunAdapter;
    private List<HomeBannerBean.DataBean.ListBean> imgList;

    //广告实体类
    private HomeBannerBean.DataBean.ListBean bean;

    public CarouseViewModel(CustomActivity mActivity, ActivityCarouselBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        //获取广告实体类
        bean = (HomeBannerBean.DataBean.ListBean) getBundle().getSerializable("advertImgBean");

        meunAdapter = new CarousePagerAdapter(mActivity.getSupportFragmentManager(),bean);
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);
        binding.mViewPager.setCurrentItem(0);
    }

    @Override
    protected void lazyLoad() {
        //读取banner
        clearParams().setParams("img_code","app_yd").setParams("img_num","4");
        Controller.myRequest(Constants.GET_IMG_LIST,Controller.TYPE_POST,getParams(), HomeBannerBean.class,this);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof HomeBannerBean){
            imgList = (((HomeBannerBean) data)).getData().getList();

            if(imgList != null && imgList.size() > 1){
                meunAdapter.setData(imgList);
                binding.mViewPager.setOffscreenPageLimit(imgList.size());
                binding.waterdrop.setViewPager(binding.mViewPager);
                binding.waterdrop.setItemNum(imgList.size());
            }else{
                imgList = new ArrayList<>();
                HomeBannerBean.DataBean.ListBean listBean = new HomeBannerBean.DataBean.ListBean();
                listBean.setImg_id(R.mipmap.banner_one);
                imgList.add(listBean);

                listBean = new HomeBannerBean.DataBean.ListBean();
                listBean.setImg_id(R.mipmap.banner_two);
                imgList.add(listBean);

                listBean = new HomeBannerBean.DataBean.ListBean();
                listBean.setImg_id(R.mipmap.banner_third);
                imgList.add(listBean);

                meunAdapter.setData(imgList);
                binding.mViewPager.setOffscreenPageLimit(imgList.size());
                binding.waterdrop.setViewPager(binding.mViewPager);
                binding.waterdrop.setItemNum(imgList.size());
            }

        }
    }
}
