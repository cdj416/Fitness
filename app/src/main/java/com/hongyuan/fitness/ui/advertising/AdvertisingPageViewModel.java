package com.hongyuan.fitness.ui.advertising;

import com.bumptech.glide.Glide;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityAdvertisingPageBinding;
import com.hongyuan.fitness.ui.main.MainActivity;
import com.hongyuan.fitness.ui.main.main_home.recommend.HomeBannerBean;
import com.hongyuan.fitness.util.BaseUtil;
import com.hongyuan.fitness.util.HourMeterUtil;
import com.hongyuan.fitness.util.JumpUtils;

public class AdvertisingPageViewModel extends CustomViewModel implements HourMeterUtil.TimeCallBack{

    private ActivityAdvertisingPageBinding binding;

    private HourMeterUtil hourMeterUtil;
    //计时秒数
    private final int SECOND = 5;

    public AdvertisingPageViewModel(CustomActivity mActivity,ActivityAdvertisingPageBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
        lazyLoad();
    }

    @Override
    protected void initView() {
        HomeBannerBean.DataBean.ListBean imgBean = (HomeBannerBean.DataBean.ListBean) getBundle().getSerializable("advertImgBean");

        hourMeterUtil = new HourMeterUtil();
        hourMeterUtil.setTimeCallBack(this);

        binding.showSecond.setOnClickListener(v -> {
            //停止计时
            hourMeterUtil.stopCount();
            startActivity(MainActivity.class,null);
            mActivity.finish();
        });

        binding.showImg.setOnClickListener(v ->{
            if(imgBean != null && BaseUtil.isValue(imgBean.getImg_href())){
                JumpUtils.JumpBeans jumpBeans = new JumpUtils.JumpBeans();
                jumpBeans.setImg_href(imgBean.getImg_href());
                jumpBeans.setHref_id(imgBean.getImg_href_id());
                jumpBeans.setHref_code(imgBean.getImg_href_code());
                jumpBeans.setImg_href_type(imgBean.getImg_href_type());
                if(imgBean.getImg_href_type() == 2){
                    hourMeterUtil.stopCount();
                    jumpBeans.setH5Title("广告");
                    jumpBeans.setH5Back("goMain");

                }
                JumpUtils.goAtherPage(mActivity,jumpBeans);
            }

        });

        //设置广告页图片
        if(imgBean != null && BaseUtil.isValue(imgBean.getImg_src())){
            Glide.with(mActivity).load(imgBean.getImg_src()).into(binding.showImg);
        }
        hourMeterUtil.startCount();

    }

    @Override
    public void onSuccess(Object data) {
    }

    @Override
    public void onTime(int passedTime) {
        if(passedTime <= 5){
            binding.showSecond.setText((SECOND-passedTime)+"跳转");
        }
        if(passedTime == 5){
            startActivity(MainActivity.class,null);
            mActivity.finish();
        }
    }
}
