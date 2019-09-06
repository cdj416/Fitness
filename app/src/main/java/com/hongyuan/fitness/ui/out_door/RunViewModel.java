package com.hongyuan.fitness.ui.out_door;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.amap.api.location.AMapLocation;
import com.hongyuan.fitness.R;
import com.hongyuan.fitness.base.CustomActivity;
import com.hongyuan.fitness.base.CustomFragment;
import com.hongyuan.fitness.base.CustomViewModel;
import com.hongyuan.fitness.databinding.ActivityRunBinding;
import com.hongyuan.fitness.ui.out_door.run.RunFragment;
import com.hongyuan.fitness.ui.out_door.wallk.WalkFragment;
import com.hongyuan.fitness.ui.out_door.wallk.my_top_view.TopTab;
import com.hongyuan.fitness.util.MyLocationUtil;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class RunViewModel extends CustomViewModel implements MyLocationUtil.GetLocation, RunFragment.Operate {
    private ActivityRunBinding binding;
    private NavigationController mNavigationController;
    private List<CustomFragment> fragments = new ArrayList<>();
    private WalkFragment walkFragment;
    private RunFragment runFragment;
    public MyLocationUtil locationUtil;

    //地图展示状态
    public boolean mapIsOpen;

    public RunViewModel(CustomActivity mActivity, ActivityRunBinding binding) {
        super(mActivity);
        this.binding = binding;
        initView();
    }

    @Override
    protected void initView() {
        mNavigationController = binding.tab.custom()
                .addItem(newItem(R.mipmap.walk_default_img,R.mipmap.walk_select_img,"步行"))
                .addItem(newItem(R.mipmap.run_default_img,R.mipmap.run_select_img,"跑步"))
                .build();

        walkFragment = new WalkFragment();
        runFragment = new RunFragment();
        runFragment.setOper(this);
        fragments.add(walkFragment);
        fragments.add(runFragment);

        binding.viewPager.setAdapter(new ViewPagerAdapter(mActivity.getSupportFragmentManager()));
        mNavigationController.setupWithViewPager(binding.viewPager);

        //获取定位实例
        locationUtil = MyLocationUtil.getInstance();
        //设置定位回调
        locationUtil.setGetLocation(this);
    }

    @Override
    public void onSuccess(Object data) {

    }

    /*
    * 定位工具类的定位信息回调
    * */
    @Override
    public void changeData(AMapLocation location) {
        //去绘制路线
        binding.myMap.changeData(location);
    }

    /*
    * fragment页面的操作回调
    * */
    @Override
    public void operate(View v) {
        switch (v.getId()){
            case R.id.startRun://开始跑步
                //开始定位
                locationUtil.startLocation();
                break;
            case R.id.runningImg://点击暂停
                //停止定位
                locationUtil.stopLocation();
                break;
            case R.id.goOnRunImg://点击继续
                //继续定位
                locationUtil.startLocation();
                break;
            case R.id.mapMark://点击了展示地图路线
                showMap();
                break;
            case R.id.isCannel://点击了结束跑步
                //停止定位
                locationUtil.stopLocation();
                break;
        }
    }

    /*
    * 打开地图
    * */
    public void showMap(){
        mapIsOpen = true;
        Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_in_anim);
        binding.myMap.setAnimation(animation);
        binding.myMap.setVisibility(View.VISIBLE);
    }
    /*
    * 关闭地图
    * */
    public void closeMap(){
        mapIsOpen = false;
        Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.dialog_out_anim);
        binding.myMap.setAnimation(animation);
        binding.myMap.setVisibility(View.GONE);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            setData();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        TopTab mainTab = new TopTab(mActivity);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFFFFFFFF);
        mainTab.setTextCheckedColor(0xFFFFFFFF);
        return mainTab;
    }
}
