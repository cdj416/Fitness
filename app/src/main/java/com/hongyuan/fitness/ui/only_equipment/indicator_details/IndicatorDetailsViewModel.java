package com.hongyuan.fitness.ui.only_equipment.indicator_details;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityIndicatorDetailsBinding;
import com.hongyuan.fitness.ui.login.vtwo_login.vtwo_verification_login.VtwoVerificationLoginActivity;

public class IndicatorDetailsViewModel extends CustomViewModel {

    private ActivityIndicatorDetailsBinding binding;

    public IndicatorDetailsViewModel(CustomActivity mActivity, ActivityIndicatorDetailsBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        IndicatorDetailsPagerAdapter meunAdapter = new IndicatorDetailsPagerAdapter(mActivity.getSupportFragmentManager());
        binding.mViewPager.setAdapter(meunAdapter);
        binding.layoutMenu.setupWithViewPager(binding.mViewPager);

        binding.mViewPager.setCurrentItem(getBundle().getInt("showPosition"));

        binding.mViewPager.addOnPageChangeListener(getOnPageChangeListener());
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
                if(position == 0){
                    mActivity.getMainTitle().getRightView().setVisibility(View.VISIBLE);
                }else{
                    mActivity.getMainTitle().getRightView().setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    public void onSuccess(Object data) {

    }
}
